$(window).on('load', function() {
    setWindowAndFont();
    $(window).resize(function(){
        setWindowAndFont();
    });
    getAllPlayers();
    setListenersOnRows();
    setListenersOnBadges();
    setSortByPositionAscOnBadge();
    setSortByAffiliation();
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

function getAllPlayers(){
    $.get('/player/complete', function(data){
        var script = document.createElement('script');
        script.setAttribute('id', 'jsonData');
        script.appendChild(document.createTextNode(JSON.stringify(data)));
        document.body.appendChild(script);
        fillTableWithPlayers(data);
    });
}

function fillTableWithPlayers(data){
    $('#tab-body').empty();
    data.forEach(function(player){
            var tr = document.createElement('tr');
            if(player.myPlayer) $(tr).addClass('table-success');
            $('#tab-body').append($(tr).append($('<td>').css('display', 'none').attr('id-value', player.id).append(player.id))
                                        .append($('<td>').append(player.transfermarktInfo.playerName + ' ').append($('<a>').attr('href', '/player/complete/'+player.id).attr('target', '_blank').append($('<i>').addClass('fa fa-external-link').attr('aria-hidden', true))))
                                        .append($('<td>').css('width', '10%').append(player.transfermarktInfo.age))
                                        .append($('<td>').css('width', '14%').append(player.pesDbInfoList[player.pesDbInfoList.length-1].primaryPosition).attr('position-value', player.pesDbInfoList[player.pesDbInfoList.length-1].positionNumberValue))
                                        .append($('<td>').css('width', '13%').append(player.pesDbInfoList[player.pesDbInfoList.length-1].overallRating))
                                        .append($('<td>').append(formatPlayerValue(player.psmlInfoList[player.psmlInfoList.length-1].teamValue) + ' ').css('text-align', 'right').css('padding-right', '3.5%').append($(' <i>').addClass(getArrowBasedOnRelation(player.psmlInfoList[player.psmlInfoList.length-1].teamValue, player.transfermarktInfo.currentValue))))
                                        .append($('<td>').append(formatPlayerValue(player.transfermarktInfo.currentValue)).css('text-align', 'right').css('padding-right', '4.5%')));
    });
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
        setBackgroundColorOnUserPlayers(jsonData, this);
        setUrlsOnBadges(selectedPlayer);
    });    
}

function fillTransfermarkInfo(selectedPlayer){
    $('#tm tr td:nth-of-type(2)').empty();
    var transfermarktInfo = selectedPlayer.transfermarktInfo;
    $('#nationalTeam').append(transfermarktInfo.nationalTeam);
    $('#clubTeam').append(transfermarktInfo.clubTeam);
    $('#contractUntil').append(transfermarktInfo.contractUntil);
    $('#currentValue').append(formatPlayerValue(transfermarktInfo.currentValue));
    $('#lastChangedCurrentValue').append(transfermarktInfo.lastChangedCurrentValue);
    $('#tm #lastMeasured').empty();
    $('#tm #lastMeasured').append('(' + transfermarktInfo.lastMeasured + ')');
}

function fillPsmlInfo(selectedPlayer){
    $('#psml tr td:nth-of-type(2)').empty();
    var psmlInfo = selectedPlayer.psmlInfoList[selectedPlayer.psmlInfoList.length-1];
    $('#psml #clubTeam').append(psmlInfo.teamName);
    $('#psmlValue').append(formatPlayerValue(psmlInfo.teamValue));
    $('#psml #lastMeasured').empty();
    $('#psml #lastMeasured').append('(' + psmlInfo.lastMeasured + ')');
}

function fillPesDbInfo(selectedPlayer){
    $('#pesDb tr td:nth-of-type(2)').empty();
    var pesDbInfo = selectedPlayer.pesDbInfoList[selectedPlayer.pesDbInfoList.length-1];
    $('#pesDbName').append(pesDbInfo.pesDbName);
    $('#teamName').append(pesDbInfo.teamName);
    $('#foot').append(pesDbInfo.foot);
    $('#weekCondition').append(pesDbInfo.weekCondition);
    $('#primaryPosition').append(pesDbInfo.primaryPosition);
    var otherStrongPositions = [];
    pesDbInfo.otherStrongPositions.forEach(function(position, i){
        otherStrongPositions = otherStrongPositions.concat('<span>'+position+'</span>');
        if(!(i === pesDbInfo.otherStrongPositions.length-1))
            otherStrongPositions = otherStrongPositions.concat(', ');
    });
    $('#otherStrongPositions').append(otherStrongPositions);
    var otherWeakPositions = [];
    pesDbInfo.otherWeakPositions.forEach(function(position, i){
        otherWeakPositions = otherWeakPositions.concat('<span>'+position+'</span>');
        if(!(i === pesDbInfo.otherWeakPositions.length-1))
            otherWeakPositions = otherWeakPositions.concat(', ');
    });    
    $('#otherWeakPositions').append(otherWeakPositions);
    $('#attackingProwess').append(pesDbInfo.attackingProwess);
    $('#ballControl').append(pesDbInfo.ballControl);
    $('#dribbling').append(pesDbInfo.dribbling);
    $('#lowPass').append(pesDbInfo.lowPass);
    $('#loftedPass').append(pesDbInfo.loftedPass);
    $('#finishing').append(pesDbInfo.finishing);
    $('#placeKicking').append(pesDbInfo.placeKicking);
    $('#swerve').append(pesDbInfo.swerve);
    $('#header').append(pesDbInfo.header);
    $('#defensiveProwess').append(pesDbInfo.defensiveProwess);
    $('#ballWinning').append(pesDbInfo.ballWinning);
    $('#kickingPower').append(pesDbInfo.kickingPower);
    $('#speed').append(pesDbInfo.speed);
    $('#explosivePower').append(pesDbInfo.explosivePower);
    $('#bodyControl').append(pesDbInfo.bodyControl);
    $('#physicalContact').append(pesDbInfo.physicalContact);
    $('#jump').append(pesDbInfo.jump);
    $('#stamina').append(pesDbInfo.stamina);
    $('#goalkeeping').append(pesDbInfo.goalkeeping);
    $('#catching').append(pesDbInfo.catching);
    $('#clearing').append(pesDbInfo.clearing);
    $('#reflexes').append(pesDbInfo.reflexes);
    $('#coverage').append(pesDbInfo.coverage);
    $('#form').append(pesDbInfo.form);
    $('#injuryResistance').append(pesDbInfo.injuryResistance);
    $('#weakFootUsage').append(pesDbInfo.weakFootUsage);
    $('#weakFootAccuracy').append(pesDbInfo.weakFootAccuracy);
    $('#overallRating').append(pesDbInfo.overallRating);
    $('#pesDb #lastMeasured').empty();
    $('#pesDb #lastMeasured').append('(' + pesDbInfo.lastMeasured + ')');
    setColorOnRatings();
    setColorOnPositions();
}

function fillWhoScoredInfo(selectedPlayer){
    $('#ws table tbody').empty();
    var whoscoredInfo = selectedPlayer.whoScoredInfoList[selectedPlayer.whoScoredInfoList.length-1];
    whoscoredInfo.coreStatsList.forEach(function(coreStats, i){
        var tr = document.createElement('tr');
        var competition = document.createElement('td');
        competition.appendChild(document.createTextNode(coreStats.competition));
        tr.appendChild(competition);
        var apps = document.createElement('td');
        if(i === selectedPlayer.whoScoredInfoList[selectedPlayer.whoScoredInfoList.length-1].coreStatsList.length-1){
            apps.appendChild(document.createTextNode(coreStats.startedApps));
        } else {
            apps.appendChild(document.createTextNode(coreStats.startedApps + '(' + coreStats.subApps + ')'));
        }
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
        $('#ws #lastMeasured').append('(' + whoscoredInfo.lastMeasured + ')');
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

function setColorOnPositions(){
    $('#primaryPosition').css('color', getColorBasedOnPosition($('#primaryPosition').html()));
    $('#otherStrongPositions span').get().forEach(function(span){
        $(span).css('color', getColorBasedOnPosition($(span).html()));
    });
    $('#otherWeakPositions span').get().forEach(function(span){
        $(span).css('color', getColorBasedOnPosition($(span).html()));
    });
}

function setBackgroundColorOnUserPlayers(jsonData, selectedRow){
    var rows = document.querySelectorAll('#left tbody tr');
    rows.forEach(function(row){
        row.removeAttribute('selected');
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
    $(selectedRow).attr('selected', 'true');
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
        setLoadingSpinner(URL.split('/')[2]);
        if(URL !== '#'){
            $.ajax({
                url: URL,
                type: 'PUT',
                success: function(updatedPlayer){
                    var siteForUpdate = URL.split('/')[2];
                    var currentlySelectedPlayerId = parseInt($('#tab-body tr[selected="selected"] td:nth-of-type(1)').get(0).textContent);
                    switch (siteForUpdate){
                        case 'transfermarkt':
                            updateDataScript(updatedPlayer, updateDataScriptTransfermarkt);
                            $('#tab-body tr:has(td[id-value="'+updatedPlayer.id+'"]) td:last-of-type').html(formatPlayerValue(updatedPlayer.transfermarktInfo.currentValue));
                            $('#tab-body tr:has(td[id-value="'+updatedPlayer.id+'"]) td:nth-of-type(6) i').removeClass();
                            $('#tab-body tr:has(td[id-value="'+updatedPlayer.id+'"]) td:nth-of-type(6) i').addClass(getArrowBasedOnRelation(updatedPlayer.psmlInfoList[updatedPlayer.psmlInfoList.length-1].teamValue, updatedPlayer.transfermarktInfo.currentValue));
                            if(updatedPlayer.id === currentlySelectedPlayerId){
                                fillTransfermarkInfo(updatedPlayer);
                            }
                            break;
                        case 'psml':
                            updateDataScript(updatedPlayer, updateDataScriptPsml);
                            $('#tab-body tr:has(td[id-value="'+updatedPlayer.id+'"]) td:nth-of-type(6)').html(formatPlayerValue(updatedPlayer.psmlInfoList[updatedPlayer.psmlInfoList.length-1].teamValue).concat(' ').concat('<i class="'+getArrowBasedOnRelation(updatedPlayer.psmlInfoList[updatedPlayer.psmlInfoList.length-1].teamValue, updatedPlayer.transfermarktInfo.currentValue)+'" aria-hidden="true"></i>'));
                            if(updatedPlayer.id === currentlySelectedPlayerId){
                                fillPsmlInfo(updatedPlayer);
                            }
                            break;
                        case 'pesdb':
                            updateDataScript(updatedPlayer, updateDataScriptPesDb);
                            $('#tab-body tr:has(td[id-value="'+updatedPlayer.id+'"]) td:nth-of-type(4)').html(updatedPlayer.pesDbInfoList[updatedPlayer.pesDbInfoList.length-1].primaryPosition);
                            $('#tab-body tr:has(td[id-value="'+updatedPlayer.id+'"]) td:nth-of-type(5)').html(updatedPlayer.pesDbInfoList[updatedPlayer.pesDbInfoList.length-1].overallRating);
                            if(updatedPlayer.id === currentlySelectedPlayerId){
                                fillPesDbInfo(updatedPlayer);
                            }                            
                            break;
                        case 'whoscored':
                            updateDataScript(updatedPlayer, updateDataScriptWhoScored);
                            if(updatedPlayer.id === currentlySelectedPlayerId){
                                fillWhoScoredInfo(updatedPlayer);
                            }  
                            break;
                    }
                    removeLoadingSpinner();
                }
            });
        }
    });
}

function setSortByPositionAscOnBadge(){
    $('#position span.badge:has(i.fa-sort-asc)').on('click', function(){
        var playerList = $('#tab-body tr').get();
        playerList.sort(function(a, b){
            var aPositionNumberValue = parseInt(a.querySelector('td:nth-of-type(4)').getAttribute('position-value'));
            var bPositionNumberValue = parseInt(b.querySelector('td:nth-of-type(4)').getAttribute('position-value'));
            return aPositionNumberValue - bPositionNumberValue;
        });
        $('#tab-body').empty();
        for (var i = 0; i < playerList.length; i++) {
            $('#tab-body').append(playerList[i]);        
        }
    });
}

function setSortByAffiliation(){
    $('#allPlayersButton').on('click', showAllPlayers);
    $('#myPlayersButton').on('click', showMyPlayers);
    $('#freePlayersButton').on('click', showFreePlayers);
}

function updateDataScript(updatedPlayer, callback){
    var jsonData = JSON.parse(document.querySelector('#jsonData').textContent);
    jsonData.forEach(function(player){
        if(player.id === updatedPlayer.id){
            callback(player, updatedPlayer);
            return false;
        }
    });
    $('#jsonData').html(JSON.stringify(jsonData));
}

function updateDataScriptTransfermarkt(currentPlayer, updatedPlayer){
    currentPlayer.transfermarktInfo = updatedPlayer.transfermarktInfo;
}

function updateDataScriptWhoScored(currentPlayer, updatedPlayer){
    currentPlayer.whoScoredInfoList[currentPlayer.whoScoredInfoList.length-1] = updatedPlayer.whoScoredInfoList[currentPlayer.whoScoredInfoList.length-1];
}

function updateDataScriptPesDb(currentPlayer, updatedPlayer){
    currentPlayer.pesDbInfoList[currentPlayer.pesDbInfoList.length-1] = updatedPlayer.pesDbInfoList[currentPlayer.pesDbInfoList.length-1];
}

function updateDataScriptPsml(currentPlayer, updatedPlayer){
    currentPlayer.psmlInfoList[currentPlayer.psmlInfoList.length-1] = updatedPlayer.psmlInfoList[currentPlayer.psmlInfoList.length-1];
}

function getArrowBasedOnRelation(psmlValue, tmValue){
    if(psmlValue === tmValue){
        return 'fa fa-minus-square';
    } else if(psmlValue > tmValue){
        return 'fa fa-arrow-circle-down';
    } else {
        return 'fa fa-arrow-circle-up';
    }
}

function getColorBasedOnPosition(position){
    switch(position){
        case 'GK':
            return 'rgb(150, 138, 1)';
            break;
        case 'CB':
        case 'LB':
        case 'RB':
            return 'rgb(18, 37, 237)';
        break;
        case 'DMF':
        case 'CMF':
        case 'LMF':
        case 'RMF':
        case 'AMF':
            return 'rgb(31, 193, 58)';
        break;
        default:
            return 'rgb(242, 0, 0)';
        break;
    }
}

function formatPlayerValue(value){
    return '€ ' + value.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
}

function setLoadingSpinner(site){
    switch(site){
        case 'transfermarkt':
           createSpinner('tm');
        break;
        case 'psml':
            createSpinner('psml');
        break;
        case 'pesdb':
            createSpinnerPesDb();
        break;
        case 'whoscored':
            createSpinnerWhoScored();
        break;
    }
}

function createSpinner(siteElementId){
    $('#'+siteElementId+' .row').append($('<div>').css({
        'height': '100%',
        'width': '100%',
        'position' : 'absolute',
        'z-index': 10,
        'top': 0,
        'left': '0',
        'background-color': 'rgba(150, 150, 150, 0.8)'
        }).addClass('spinner').append($('<img>').attr('src', '/Gear-3.6s-200px.gif').css({'max-height': '100%', 'max-width': '100%', 'margin-left': '30%'})));
}

function createSpinnerPesDb(){
    $('#pesDb .row').append($('<div>').css({
        'height': '100%',
        'width': '100%',
        'position' : 'absolute',
        'z-index': 10,
        'top': 0,
        'left': '0',
        'background-color': 'rgba(150, 150, 150, 0.8)'
        }).addClass('spinner').append($('<img>').attr('src', '/Gear-3.6s-200px.gif').css({'max-height': '100%', 'max-width': '100%', 'margin': '12% auto', 'display': 'block'})));
}

function createSpinnerWhoScored(){
    $('#ws .row').append($('<div>').css({
        'height': '100%',
        'width': '100%',
        'position' : 'absolute',
        'z-index': 10,
        'top': 0,
        'left': '0',
        'background-color': 'rgba(150, 150, 150, 0.8)'
        }).addClass('spinner').append($('<img>').attr('src', '/Gear-3.6s-200px.gif').css({'height': '80%', 'display': 'block', 'margin': '1em auto'})));
}

function removeLoadingSpinner(){
    $('#tm .row .spinner').remove();
    $('#psml .row .spinner').remove();
    $('#pesDb .row .spinner').remove();
    $('#ws .row .spinner').remove();
}

function showAllPlayers(){
    var jsonData = JSON.parse(document.querySelector('#jsonData').textContent);
    fillTableWithPlayers(jsonData);
}

function showMyPlayers(){
    var jsonData = JSON.parse(document.querySelector('#jsonData').textContent);
    var myPlayers = jsonData.filter(player => player.myPlayer);
    fillTableWithPlayers(myPlayers);
}

function showFreePlayers(){
    var jsonData = JSON.parse(document.querySelector('#jsonData').textContent);
    var freePlayers = jsonData.filter(player => player.psmlInfoList[player.psmlInfoList.length-1].teamName === 'FA');
    fillTableWithPlayers(freePlayers);
}