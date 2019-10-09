$('#search').keyup(function(){
    $('#content').empty();
    if(this.value.length > 2){
        $.get('/player/'+this.value+'/name', function(data){
            var ol = document.createElement('ol');
            data.forEach(function(player){
                var li = document.createElement('li');
                var value = document.createTextNode(player.playerName);
                li.appendChild(value);
                ol.appendChild(li);
            });
            $('body').append($('<div>').append(ol));
        });
    }
});