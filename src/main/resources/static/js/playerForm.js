$(window).on('load', function() {
    setWindowAndFont();
    setListenerForSearchingExistingPlayers();
    setListenerOnAddExistingPlayerButton();
});

function setWindowAndFont(){
    if(window.screen.availWidth >= 1600){
        document.body.style.fontSize = '16px';
    } else {
        document.body.style.fontSize = '13px';
    }
    $('#addPlayerSearchResult').css('height', window.innerHeight - 797)
    $('#addPlayerSearchResult tbody').css('height', $('#addPlayerSearchResult').height()-26);
}

function setListenerForSearchingExistingPlayers(){
    $('#addPlayerSearchInput').keyup(function(){
        var tbody = $('#addPlayerSearchResult tbody').get(0);
        $(tbody).empty();
        if(this.value.length > 2){
            $.get('/player/'+this.value+'/name', function(data){
                data.forEach(function(player){
                    $(tbody).append($('<tr>').append($('<td>').append(player.playerName))
                                             .append($('<td>').append(player.position))
                                             .append($('<td>').append(player.overallRating))
                                             .append($('<td>').append(player.psmlTeam))
                                             .append($('<td>').append(formatPlayerValue(player.tmCurrentValue)))
                                             .append($('<td>').append(formatPlayerValue(player.psmlValue)))
                                             .append($('<td>').append($('<button>').append('Add').attr({'value': player.id, 'data-my-player': player.myPlayer}).addClass('btn btn-success btn-sm').addClass(player.myPlayer===true?'disabled':''))));

                });
            });
        }
    });
}

function setListenerOnAddExistingPlayerButton(){
    $('#addPlayerSearchResult tbody').on('click', 'tr button:not([class*=disabled])', function(){
        $.post('/player/'+this.value+'/add', {
                isUserPlayer: this.getAttribute('data-my-player')
        }).done(function(){
                $('div.alert').html('The player was added successfully. <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
                $('div.alert').addClass('alert-success');
                $('form input').val('');
                $('form input[type=checkbox]').prop('checked', false);
                $('div.alert').fadeIn(2000);
                setTimeout(hideAlert, 4000);
        }).fail(function(){
                $('div.alert').html('The player was not added successfully. <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
                $('div.alert').addClass('alert-danger');
                $('div.alert').fadeIn(2000);
                setTimeout(hideAlert, 4000);
        })
        });
}

function hideAlert(){
	$('div.alert').fadeOut(2000, function(){
		$('div.alert').html('');
		$('div.alert').removeClass('alert-success alert-danger');
	});
}

function formatPlayerValue(value){
    return '€ ' + Number.parseFloat(value).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
}