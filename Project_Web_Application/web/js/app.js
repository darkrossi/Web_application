$(document).ready(function ()
{
    var animationIsRunning = false;

    $('#fullpage').fullpage({
        /*paddingTop: '7em',*/
        resize: false,
        slidesNavigation: true,
        slidesNavPosition: 'bottom',
        verticalCentered: false,
        //sectionsColor : ['#ECECEC', '#FFF', '#ECECEC', '#FFF', '#ECECEC', '#ECECEC']
        afterLoad: function (anchorLink, index)
        {
            //using index
            if (index == 2)
            {
                animationSmartphoneApp();
                animationStory();
            }
        },
        onLeave: function (index, nextIndex, direction) {
            //after leaving section 2
            if (index == 2 && direction == 'down')
            {
                animationIsRunning = true;

                // Story
                $('#box-2').fadeOut().css('top', '-1000px');
                $('#box-1').fadeOut().css('top', '-1000px');
            }
        }
    });

    // Avatar
    $('img#smartphone').hide().css({left: '10000px'});
    $('#model-side').hide();
    $('#avatar-1').hide();
    $('#avatar-2').hide();
    $('#avatar-3').hide();

    // Avatar (try)
    $('#try #try-2').hide();
    $('#try #try-3').hide();
    $('#try #try-4').hide();

    // Story
    $('#box-2').hide().css('top', '-1000px');
    $('#box-1').hide().css('top', '-1000px');

    function animationSmartphoneApp()
    {
        if (!animationIsRunning)
        {
            animationIsRunning = true;

            $('#avatar-3').hide();

            $('img#smartphone').show().animate({left: '25px'}, 1500, function ()
            {
                $('#model-front').delay(1500).effect('pulsate', {times: 1}, 200).fadeOut(function ()
                {
                    $('#model-side').fadeIn().delay(1000).effect('pulsate', {times: 1}, 200, function ()
                    {
                        $(this).fadeOut(function ()
                        {
                            $('img#smartphone').delay(200).animate({left: '10000px'}, 500, function ()
                            {
                                $(this).hide();

                                $('#avatar-1').fadeIn(500, function ()
                                {
                                    $('#avatar-1').delay(1500).fadeOut(500, function ()
                                    {
                                        $('#avatar-2').fadeIn(500, function ()
                                        {
                                            $('#avatar-2').delay(1500).fadeOut(500, function ()
                                            {
                                                $('#avatar-3').fadeIn(500, function ()
                                                {
                                                    /*$('#avatar-3').delay(5000).fadeOut(500, function()
                                                     {
                                                     animationIsRunning = false;
                                                     //animationSmartphoneApp();	
                                                     });*/
                                                })
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    });
                });

            });
        }
    }

    $('#comitment span.shortcut').each(function () {
        $(this).hide();
    });

    $('span.plus-1').click(function () {
        $(this).hide();
        $('span.dot-1').hide();
        $('span.shortcut-1').fadeIn();
    });

    $('span.plus-2').click(function () {
        $(this).hide();
        $('span.dot-2').hide();
        $('span.shortcut-2').fadeIn();
    });

    function animationStory()
    {
        $('#box-2').delay(500).show().animate({'top': '0'}, 1000);
        $('#box-1').show().animate({'top': '0'}, 1000);
    }

    $('#try figure').find('span.text-content').mouseover(function ()
    {
        $(this).fadeTo('fast', 1);
    });

    $('#try figure').find('span.text-content').mouseout(function ()
    {
        $(this).fadeTo('fast', 0);
    });

    var top = 0;
    var bottom = 0;

    function displayClothesId()
    {
        if ((top == 0 && bottom == 0) || (top == 1 && bottom == 0) || (top == 0 && bottom == 1))
        {
            return 0;
        }
        else if (top == 2 && bottom == 0)
        {
            return 3;
        }
        else if (top == 0 && bottom == 2)
        {
            return 2;
        }
        else
        {
            if (top == 1)
            {
                if (bottom == 1)
                {
                    return 1;
                }
                else
                {
                    return 2;
                }
            }
            else
            {
                if (bottom == 1)
                {
                    return 3;
                }
                else
                {
                    return 4;
                }
            }
        }
    }

    function hideAllClothes()
    {
        $('#try #try-1').hide();
        $('#try #try-2').hide();
        $('#try #try-3').hide();
        $('#try #try-4').hide();
    }

    function generateClothes()
    {
        var generatedClothes = displayClothesId();

        hideAllClothes();

        switch (generatedClothes)
        {
            case 0:
                $('#try #try-1').fadeIn();
                break;
            case 1:
                $('#try #try-1').fadeIn();
                break;
            case 2:
                $('#try #try-2').fadeIn();
                break;
            case 3:
                $('#try #try-3').fadeIn();
                break;
            case 4:
                $('#try #try-4').fadeIn();
                break;
            default:
                $('#try #try-1').fadeIn();
        }
    }

    $('#try .item-1').click(function ()
    {
        top = 1;

        generateClothes();
    });

    $('#try .item-2').click(function ()
    {
        top = 2;

        generateClothes();
    });

    $('#try .item-3').click(function ()
    {
        bottom = 1;

        generateClothes();
    });

    $('#try .item-4').click(function ()
    {
        bottom = 2;

        generateClothes();
    });

    $('.popup').click(function (e) {
        $('#basic-modal-content').modal({overlayClose: true/*, autoResize: true*/});

        return false;
    });
});