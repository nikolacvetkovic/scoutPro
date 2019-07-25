$(window).on('load', function() {
    $('button').on('click', function(){
    	var transfermarktUrlValue = $('#transfermarktUrl').val();
    	var whoScoredUrlValue = $('#whoScoredUrl').val();
    	var pesDbUrlValue = $('#pesDbUrl').val();
    	var psmlUrlValue = $('#psmlUrl').val();
    	var myPlayerValue = false;
    	if($('#myPlayer').is(':checked')){
    		myPlayerValue = true;
    	}
    	$.post('/player/new', {
    		transfermarktUrl: transfermarktUrlValue,
    		whoScoredUrl: whoScoredUrlValue,
    		pesDbUrl: pesDbUrlValue,
    		psmlUrl: psmlUrlValue,
    		myPlayer: myPlayerValue
    	}).done(function(){
    		$('div.alert').html('The player was added successfully. <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
    		$('div.alert').addClass('alert-success');
    		$('form input').val('');
    		$('#myPlayer').prop('checked', false);
    		$('div.alert').fadeIn(2000);
    		setTimeout(hideAlert, 4000);
    	}).fail(function(){
    		$('div.alert').html('The player was not added successfully. <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
    		$('div.alert').addClass('alert-danger');
    		$('div.alert').fadeIn(2000);
    		setTimeout(hideAlert, 4000);
    	});
    });
});

function hideAlert(){
	$('div.alert').fadeOut(2000, function(){
		$('div.alert').html('');
		$('div.alert').removeClass('alert-success alert-danger');
	});
}