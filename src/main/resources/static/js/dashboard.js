$(window).on('load', function() {
    setWindowAndFont();
    $(window).resize(function(){
        setWindowAndFont();
    });
    fillTableWithPlayers();
    setListenersOnRows();
});

function setWindowAndFont(){
    if(window.screen.availWidth >= 1600){
        document.body.style.fontSize = '16px';
    } else {
        document.body.style.fontSize = '13px';
    }
    $('#left').css('height', window.innerHeight - 155);
    $('#left table tbody').css('height', $('#left').height()-34);
    $('#right').css('height', window.innerHeight - 155);
    $('#right table tbody').css('height', $('#right').height()-34);
}

function fillTableWithPlayers(){
    $.get('/player/complete', function (data) {
        data.forEach(function(player){
            var table = document.getElementById('tab-body');
            var tr = document.createElement('tr');
            if(player.myPlayer) tr.className = 'table-success';
            var hiddenTd = document.createElement('td');
            hiddenTd.style.display = 'none';
            hiddenTd.appendChild(document.createTextNode(player.id));
            var td1 = document.createElement('td');
            td1.appendChild(document.createTextNode(player.transfermarktInfo.playerName));
            var td9 = document.createElement('td');
            td9.appendChild(document.createTextNode(player.transfermarktInfo.age));
            td9.style.width = '10%';
            var td2 = document.createElement('td');
            td2.appendChild(document.createTextNode(player.pesDbInfoList[player.pesDbInfoList.length-1].primaryPosition));
            td2.style.width = '14%';
            var td3 = document.createElement('td');
            td3.appendChild(document.createTextNode(player.pesDbInfoList[player.pesDbInfoList.length-1].overallRating));
            td3.style.width = '13%';
            var td8 = document.createElement('td');
            td8.appendChild(document.createTextNode('€ ' + player.psmlInfoList[player.psmlInfoList.length-1].teamValue.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')));
            var td4 = document.createElement('td');
            td4.appendChild(document.createTextNode('€ ' + player.transfermarktInfo.currentValue.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')));

            tr.appendChild(hiddenTd);
            tr.appendChild(td1);
            tr.appendChild(td9);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td8);
            tr.appendChild(td4);

            table.appendChild(tr);
        });
        prepareJsonScript(data);
    });
}

function prepareJsonScript(data){
    var script = document.createElement('script');
    script.setAttribute('id', 'jsonData');
    script.appendChild(document.createTextNode(JSON.stringify(data)));
    document.body.appendChild(script);
}

function setListenersOnRows(){
    $('#left tbody').on('click', 'tr', function(){
        var jsonData = JSON.parse(document.querySelector('#jsonData').textContent);
        var selectedPlayerId = parseInt($(this).find('td:nth-of-type(1)').get(0).textContent);

        var selectedPlayer = null;
        jsonData.forEach(function(player){
            if(selectedPlayerId === player.id)
                selectedPlayer = player;
        });
        fillTransfermarkInfo(selectedPlayer);
        fillPsmlInfo(selectedPlayer);
        fillWhoScoredInfo(selectedPlayer);
        fillPesDbInfo(selectedPlayer);
        setColorOnRatings();
        setBackgroundColorOnUserPlayers(jsonData, this);
        setUrlsOnBadges(selectedPlayer);
        setListenersOnBadges();
    });    
}

function fillTransfermarkInfo(selectedPlayer){
    $('#tm tr td:nth-of-type(2)').empty();
    $('#nationalTeam').append(selectedPlayer.transfermarktInfo.nationalTeam);
    $('#clubTeam').append(selectedPlayer.transfermarktInfo.clubTeam);
    $('#contractUntil').append(selectedPlayer.transfermarktInfo.contractUntil);
    $('#currentValue').append(selectedPlayer.transfermarktInfo.currentValue.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') + ' €');
    $('#lastChangedCurrentValue').append(selectedPlayer.transfermarktInfo.lastChangedCurrentValue);
    $('#tm #lastMeasured').empty();
    $('#tm #lastMeasured').append('(' + selectedPlayer.transfermarktInfo.lastMeasured + ')');
}

function fillPsmlInfo(selectedPlayer){
    $('#psml tr td:nth-of-type(2)').empty();
    $('#psml #clubTeam').append(selectedPlayer.psmlInfoList[selectedPlayer.psmlInfoList.length-1].teamName);
    $('#psmlValue').append(selectedPlayer.psmlInfoList[selectedPlayer.psmlInfoList.length-1].teamValue.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') + ' €');
    $('#psml #lastMeasured').empty();
    $('#psml #lastMeasured').append('(' + selectedPlayer.psmlInfoList[selectedPlayer.psmlInfoList.length-1].lastMeasured + ')');
}

function fillPesDbInfo(selectedPlayer){
    $('#pesDb tr td:nth-of-type(2)').empty();
    $('#pesDbName').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].pesDbName);
    $('#teamName').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].teamName);
    $('#foot').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].foot);
    $('#weekCondition').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].weekCondition);
    $('#primaryPosition').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].primaryPosition);
    $('#otherStrongPositions').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].otherStrongPositions.join(', '));
    $('#otherWeakPositions').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].otherWeakPositions.join(', '));
    $('#attackingProwess').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].attackingProwess);
    $('#ballControl').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].ballControl);
    $('#dribbling').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].dribbling);
    $('#lowPass').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].lowPass);
    $('#loftedPass').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].loftedPass);
    $('#finishing').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].finishing);
    $('#placeKicking').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].placeKicking);
    $('#swerve').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].swerve);
    $('#header').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].header);
    $('#defensiveProwess').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].defensiveProwess);
    $('#ballWinning').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].ballWinning);
    $('#kickingPower').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].kickingPower);
    $('#speed').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].speed);
    $('#explosivePower').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].explosivePower);
    $('#bodyControl').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].bodyControl);
    $('#physicalContact').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].physicalContact);
    $('#jump').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].jump);
    $('#stamina').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].stamina);
    $('#goalkeeping').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].goalkeeping);
    $('#catching').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].catching);
    $('#clearing').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].clearing);
    $('#reflexes').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].reflexes);
    $('#coverage').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].coverage);
    $('#form').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].form);
    $('#injuryResistance').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].injuryResistance);
    $('#weakFootUsage').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].weakFootUsage);
    $('#weakFootAccuracy').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].weakFootAccuracy);
    $('#overallRating').append(selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].overallRating);
    $('#pesDb #lastMeasured').empty();
    $('#pesDb #lastMeasured').append('(' + selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1].lastMeasured + ')');
}

function fillWhoScoredInfo(selectedPlayer){
    $('#ws table tbody').empty();
    selectedPlayer.whoScoredInfoList[selectedPlayer.whoScoredInfoList.length-1].coreStatsList.forEach(function(coreStats){
        var tr = document.createElement('tr');
        var competition = document.createElement('td');
        competition.appendChild(document.createTextNode(coreStats.competition));
        tr.appendChild(competition);
        var apps = document.createElement('td');
        apps.appendChild(document.createTextNode(coreStats.startedApps + '(' + coreStats.subApps + ')'));
        tr.appendChild(apps);
        var mins = document.createElement('td');
        mins.appendChild(document.createTextNode(coreStats.mins));
        tr.appendChild(mins);
        var goals = document.createElement('td');
        goals.appendChild(document.createTextNode(coreStats.goals));
        tr.appendChild(goals);
        var assists = document.createElement('td');
        assists.appendChild(document.createTextNode(coreStats.assists));
        tr.appendChild(assists);
        var yel = document.createElement('td');
        yel.appendChild(document.createTextNode(coreStats.yellowCards));
        tr.appendChild(yel);
        var red = document.createElement('td');
        red.appendChild(document.createTextNode(coreStats.redCards));
        tr.appendChild(red);
        var spg = document.createElement('td');
        spg.appendChild(document.createTextNode(coreStats.shotsPerGame));
        tr.appendChild(spg);
        var ps = document.createElement('td');
        ps.appendChild(document.createTextNode(coreStats.passSuccess));
        tr.appendChild(ps);
        var aerials = document.createElement('td');
        aerials.appendChild(document.createTextNode(coreStats.aerialsWon));
        tr.appendChild(aerials);
        var motm = document.createElement('td');
        motm.appendChild(document.createTextNode(coreStats.manOfTheMatch));
        tr.appendChild(motm);
        var rating = document.createElement('td');
        rating.appendChild(document.createTextNode(coreStats.rating));
        tr.appendChild(rating);
        $('#ws tbody tr').css('font-weight', 'normal');
        $('#ws tbody:last-child').css('font-weight', 'bold');
        $('#ws table tbody').append(tr);
        $('#ws #lastMeasured').empty();
        $('#ws #lastMeasured').append('(' + selectedPlayer.whoScoredInfoList[selectedPlayer.whoScoredInfoList.length-1].lastMeasured + ')');
    });
}

function setColorOnRatings(){
    var ratings = document.querySelectorAll('#pesDb table tr td:nth-of-type(2)');
    ratings.forEach(function(rating){
        rating.style.color = 'black';
        if(!isNaN(rating.textContent)){
            if(rating.textContent >= 95){
                rating.style.color = 'red';
            } else if(rating.textContent >= 90){
                rating.style.color = 'rgb(214, 40, 31)';
            } else if(rating.textContent >= 80){
                rating.style.color = 'orange';
            } else if(rating.textContent >= 75){
                rating.style.color = 'rgb(31, 193, 58)';
            }
        }
    });
}

function setBackgroundColorOnSelectedPlayer(){
    $('#left tbody tr.table-danger').removeClass('table-danger');
    $(this).addClass('table-danger');
}

function setBackgroundColorOnUserPlayers(jsonData, selectedRow){
    var rows = document.querySelectorAll('#left tbody tr');
    rows.forEach(function(row){
        row.className = '';
        row.style.fontWeight = 'normal';
        var rowId = parseInt(row.querySelector('td:nth-of-type(1)').textContent);
        for(var i = 0; i < jsonData.length; i++){
            if(jsonData[i].id === rowId && jsonData[i].myPlayer){
                row.className = 'table-success';
                break;
            }
        }
    });
    $(selectedRow).removeClass('table-success');
    $(selectedRow).css('font-weight', 'bold');
    $(selectedRow).addClass('table-danger');
}

function setUrlsOnBadges(selectedPlayer){
    $('#tm h6 span.badge').attr('data-url', '/player/transfermarkt/' + selectedPlayer.id);
    $('#psml h6 span.badge').attr('data-url', '/player/psml/' + selectedPlayer.id);
    $('#pesDb h6 span.badge').attr('data-url', '/player/pesdb/' + selectedPlayer.id);
    $('#ws h6 span.badge').attr('data-url', '/player/whoscored/' + selectedPlayer.id);
}

function setListenersOnBadges(){
    $('span.badge[data-url]').on('click', function(){
        var URL = $(this).attr('data-url');
        if(URL !== '#'){
            $.ajax({
                url: URL,
                type: 'PUT',
                success: function(updatedPlayer){
                    var siteForUpdate = URL.split('/')[2];
                    switch (siteForUpdate){
                        case 'transfermarkt':
                            fillTransfermarkInfo(updatedPlayer);
                            break;
                        case 'psml':
                            fillPsmlInfo(updatedPlayer);
                            break;
                        case 'pesdb':
                            fillPesDbInfo(updatedPlayer);
                            break;
                        case 'whoscored':
                            fillWhoScoredInfo(updatedPlayer);
                            break;
                    }
                }
            });
        }
    });
}