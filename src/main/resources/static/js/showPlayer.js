$(window).on('load', function() {
    setColorOnRatings();
    setColorOnPositions();
});

function setColorOnRatings(){
    $('#pesDb table tr td:nth-of-type(2)').get().forEach(function(rating){
        $(rating).css('color', getColorBasedOnRating($(rating).text()));
    });
    var mainOverall = $('#mainOverall').get();
    $(mainOverall).css('color', getColorBasedOnRating($(mainOverall).text()));
}

function setColorOnPositions(){
    $('#primaryPosition').css('color', getColorBasedOnPosition($('#primaryPosition').html()));
    $('#otherStrongPositions').get().forEach(function(span){
        $(span).css('color', getColorBasedOnPosition($(span).text()));
    });
    $('#otherWeakPositions span').get().forEach(function(span){
        $(span).css('color', getColorBasedOnPosition($(span).text(), true));
    });
    var mainPosition = $('#mainPosition').get();
    $(mainPosition).css('color', getColorBasedOnPosition($(mainPosition).text()));
}

function getColorBasedOnRating(rating){
    if(!isNaN(rating)){
        if((rating) >= 95){
            return 'red';
        } else if(rating >= 90){
            return 'rgb(214, 40, 31)';
        } else if(rating >= 80){
            return 'orange';
        } else if(rating >= 75){
            return 'rgb(31, 193, 58)';
        }
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