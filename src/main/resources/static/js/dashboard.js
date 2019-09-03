$(window).on('load', function() {
    setWindowAndFont();
    $(window).resize(function(){
        setWindowAndFont();
    });
    getAllPlayers();
    setListenersOnRows();
    setListenersOnBadges();
    setSortByPositionAscOnBadge();
    setFilterByAffiliation();
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
    $.get('/player/0/page', function(data){
//        var script = document.createElement('script');
//        script.setAttribute('id', 'jsonData');
//        script.appendChild(document.createTextNode(JSON.stringify(data)));
//        document.body.appendChild(script);
        fillTableWithPlayers(data);
    });
}

function fillTableWithPlayers(data){
    $('#tab-body').empty();
    data.forEach(function(player, index){
            var keys = Object.keys(player);
            var tr = document.createElement('tr');
            if(player.myPlayer) $(tr).addClass('table-success');
            $('#tab-body').append($(tr).append($('<td>').attr('id', 'id-value').append('').hide())
                                      .append($('<td>').attr('id', 'playerName').append(''))
                                      .append($('<td>').attr('id', 'age').css('width', '10%').append(''))
                                      .append($('<td>').attr('id', 'position').css('width', '14%').append(''))
                                      .append($('<td>').attr('id', 'overall').css('width', '13%').append(''))
                                      .append($('<td>').attr('id', 'psmlValue').css('text-align', 'right').css('padding-right', '3.5%').append(''))
                                      .append($('<td>').attr('id', 'tmValue').css('text-align', 'right').css('padding-right', '4.5%').append('')));
            keys.forEach(function(key){
                switch(key){
                    case 'id': $('#id-value').append(player.id); break;
                    case 'playerName': $('#playerName').attr('id', key+'-'+player.id).append(player.playerName + ' ').append($('<a>').attr('href', '/player/'+ player.id +'/show').attr('target', '_blank').append($('<i>').addClass('fas fa-external-link-alt').css('color', 'black'))); break;
                    case 'age': $('#age').attr('id', key+'-'+player.id).append(player.age); break;
                    case 'position': $('#position').attr('id', key+'-'+player.id).append(player.position); break;
                    case 'overall': $('#overall').attr('id', key+'-'+player.id).append(player.overall); break;
                    case 'psmlValue': $('#psmlValue').attr('id', key+'-'+player.id).append(formatPlayerValue(player.psmlValue) + ' ').append($(' <i>').addClass(getArrowBasedOnRelation(player.psmlValue, player.tmValue))); break;
                    case 'tmValue': $('#tmValue').attr('id', key+'-'+player.id).append(formatPlayerValue(player.tmValue)); break;
                    case 'otherStrongPositions': {
                        $(tr).append($('<td>').attr('id', key+'-'+player.id).hide());
                        player.otherStrongPositions.forEach(function(position, index){
                            $('#'+key+'-'+player.id).append($('<span> ').css('padding-right', '10px').append(position));
                        });
                    } break;
                    case 'otherWeakPositions': {
                        $(tr).append($('<td>').attr('id', key+'-'+player.id).hide());
                        player.otherWeakPositions.forEach(function(position, index){
                            $('#'+key+'-'+player.id).append($('<span> ').css('padding-right', '10px').append(position));
                        });
                    } break;
                    default: $(tr).append($('<td>').attr('id', key+'-'+player.id).append(player[key]).hide());
                }
            });
    });
}

function setListenersOnRows(){
    $('#left tbody').on('click', 'tr', function(){
        fillTransfermarkInfo(this);
        fillPsmlInfo(this);
        fillPesDbInfo(this);
        fillWhoScoredInfo(this);
//        setBackgroundColorOnUserPlayers(jsonData, this);
//        setUrlsOnBadges(selectedPlayer);
    });    
}

function fillTransfermarkInfo(selectedPlayerTr){
    $('#tm tr td:nth-of-type(2)').empty();
    $('#nationalTeam').append($(selectedPlayerTr).find('td[id*=nationalTeam]').text());
    $('#clubTeam').append($(selectedPlayerTr).find('td[id*=clubTeam]').text());
    $('#contractUntil').append($(selectedPlayerTr).find('td[id*=contractUntil]').text());
    $('#tmValue').append($(selectedPlayerTr).find('td[id*=tmValue]').text());
//    $('#tm #lastMeasured').empty();
//    $('#tm #lastMeasured').append('(' + transfermarktInfo.lastMeasured + ')');
}

function fillPsmlInfo(selectedPlayerTr){
    $('#psml tr td:nth-of-type(2)').empty();
    $('#psmlTeam').append($(selectedPlayerTr).find('td[id*=psmlTeam]').text());
    $('#psmlValue').append($(selectedPlayerTr).find('td[id*=psmlValue]').text());
    $('#psmlLastCheck').empty();
    $('#psmlLastCheck').append('(' + $(selectedPlayerTr).find('td[id*=psmlLastCheck]').text() + ')');
}

function fillPesDbInfo(selectedPlayerTr){
    $('#pesDb tr td:nth-of-type(2)').empty();
    $('#pesDbPlayerName').append($(selectedPlayerTr).find('td[id*=pesDbPlayerName]').text());
    $('#pesDbTeamName').append($(selectedPlayerTr).find('td[id*=pesDbTeamName]').text());
    $('#foot').append($(selectedPlayerTr).find('td[id*=foot]').text());
    $('#weekCondition').append($(selectedPlayerTr).find('td[id*=weekCondition]').text());
    $('#primaryPosition').append($(selectedPlayerTr).find('td[id*=position]').text());
    $('#otherStrongPositions').append($(selectedPlayerTr).find('td[id*=otherStrongPositions]').html());
    $('#otherWeakPositions').append($(selectedPlayerTr).find('td[id*=otherWeakPositions]').html());
    $('#attackingProwess').append($(selectedPlayerTr).find('td[id*=attackingProwess]').text());
    $('#ballControl').append($(selectedPlayerTr).find('td[id*=ballControl]').text());
    $('#dribbling').append($(selectedPlayerTr).find('td[id*=dribbling]').text());
    $('#lowPass').append($(selectedPlayerTr).find('td[id*=lowPass]').text());
    $('#loftedPass').append($(selectedPlayerTr).find('td[id*=loftedPass]').text());
    $('#finishing').append($(selectedPlayerTr).find('td[id*=finishing]').text());
    $('#placeKicking').append($(selectedPlayerTr).find('td[id*=placeKicking]').text());
    $('#swerve').append($(selectedPlayerTr).find('td[id*=swerve]').text());
    $('#header').append($(selectedPlayerTr).find('td[id*=header]').text());
    $('#defensiveProwess').append($(selectedPlayerTr).find('td[id*=defensiveProwess]').text());
    $('#ballWinning').append($(selectedPlayerTr).find('td[id*=ballWinning]').text());
    $('#kickingPower').append($(selectedPlayerTr).find('td[id*=kickingPower]').text());
    $('#speed').append($(selectedPlayerTr).find('td[id*=speed]').text());
    $('#explosivePower').append($(selectedPlayerTr).find('td[id*=explosivePower]').text());
    $('#unwaveringBalance').append($(selectedPlayerTr).find('td[id*=unwaveringBalance]').text());
    $('#physicalContact').append($(selectedPlayerTr).find('td[id*=physicalContact]').text());
    $('#jump').append($(selectedPlayerTr).find('td[id*=jump]').text());
    $('#goalkeeping').append($(selectedPlayerTr).find('td[id*=goalkeeping]').text());
    $('#gkCatch').append($(selectedPlayerTr).find('td[id*=gkCatch]').text());
    $('#clearing').append($(selectedPlayerTr).find('td[id*=clearing]').text());
    $('#reflexes').append($(selectedPlayerTr).find('td[id*=reflexes]').text());
    $('#coverage').append($(selectedPlayerTr).find('td[id*=coverage]').text());
    $('#stamina').append($(selectedPlayerTr).find('td[id*=stamina]').text());
    $('#weakFootUsage').append($(selectedPlayerTr).find('td[id*=weakFootUsage]').text());
    $('#weakFootAccuracy').append($(selectedPlayerTr).find('td[id*=weakFootAccuracy]').text());
    $('#form').append($(selectedPlayerTr).find('td[id*=form]').text());
    $('#injuryResistance').append($(selectedPlayerTr).find('td[id*=injuryResistance]').text());
    $('#overall').append($(selectedPlayerTr).find('td[id*=overall]').text());
    $('#pesDbLastCheck').empty();
    $('#pesDbLastCheck').append('(' + $(selectedPlayerTr).find('td[id*=pesDbLastCheck]').text() + ')');
    setColorOnRatings();
    setColorOnPositions();
}

function fillWhoScoredInfo(selectedPlayerTr){
    $('#ws tr td').empty();
    $('#totalStartedApps').append($(selectedPlayerTr).find('td[id*=totalStartedApps]').text());
    $('#totalMins').append($(selectedPlayerTr).find('td[id*=totalMins]').text());
    $('#totalGoals').append($(selectedPlayerTr).find('td[id*=totalGoals]').text());
    $('#totalAssists').append($(selectedPlayerTr).find('td[id*=totalAssists]').text());
    $('#averageShotsPerGame').append($(selectedPlayerTr).find('td[id*=averageShotsPerGame]').text());
    $('#averagePassSuccess').append($(selectedPlayerTr).find('td[id*=averagePassSuccess]').text());
    $('#averageAerialsWon').append($(selectedPlayerTr).find('td[id*=averageAerialsWon]').text());
    $('#totalManOfTheMatch').append($(selectedPlayerTr).find('td[id*=totalManOfTheMatch]').text());
    $('#averageRating').append($(selectedPlayerTr).find('td[id*=averageRating]').text());
    $('#statisticsLastCheck').empty();
    $('#statisticsLastCheck').append('(' + $(selectedPlayerTr).find('td[id*=statisticsLastCheck]').text() + ')');
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
    $('#otherStrongPositions').get().forEach(function(span){
        $(span).css('color', getColorBasedOnPosition($(span).text()));
    });
    $('#otherWeakPositions span').get().forEach(function(span){
        $(span).css('color', getColorBasedOnPosition($(span).text(), true));
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

function setFilterByAffiliation(){
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
        return 'fas fa-minus-square';
    } else if(psmlValue > tmValue){
        return 'fas fa-arrow-circle-down';
    } else {
        return 'fas fa-arrow-circle-up';
    }
}

function getColorBasedOnPosition(position, isWeak){
    switch(position){
        case 'GK':
            return isWeak?'rgba(150, 138, 1, 0.6)':'rgba(150, 138, 1, 1)';
            break;
        case 'CB':
        case 'LB':
        case 'RB':
            return isWeak?'rgba(18, 37, 237, 0.6)':'rgba(18, 37, 237, 1)';
        break;
        case 'DMF':
        case 'CMF':
        case 'LMF':
        case 'RMF':
        case 'AMF':
            return isWeak?'rgba(31, 193, 58, 0.6)':'rgba(31, 193, 58, 1)';
        break;
        default:
            return isWeak?'rgba(242, 0, 0, 0.6)':'rgba(242, 0, 0, 1)';
        break;
    }
}

function formatPlayerValue(value){
    return '€ ' + Number.parseFloat(value).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
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