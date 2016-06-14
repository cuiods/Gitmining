/**
 * Created by lenovo on 2016/5/12.
 */
$(document).ready(function () {

    var searchOrange = $('#search-orange').searchMeme({ onSearch: function (searchText) {

        setTimeout(function () {

            searchOrange.searchMeme({ searchComplete: true });

            $('#search-results').html("You searched for " + searchOrange.val() + "");

            $('.panel').animate({ 'height': 200 }, 500);

        }, 3000);

    }

        , buttonPlacement: 'left', button: 'orange'

    });
    
    







});