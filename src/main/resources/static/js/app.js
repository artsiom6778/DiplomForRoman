
$(function (){
    (function ($) {
        $(".contact-form").submit(function (event) {
            event.preventDefault();

            // Сообщения формы
            let successSendText = "Сообщение успешно отправлено";
            let errorSendText = "Сообщение не отправлено. Попробуйте еще раз!";
            let requiredFieldsText = "Заполните поля с именем и телефоном";

            // Сохраняем в переменную класс с параграфом для вывода сообщений об отправке
            let message = $(this).find(".contact-form__message");

            let form = $("#" + $(this).attr("id"))[0];
            let fd = new FormData(form);
            $.ajax({
                url: "/sendtobot",
                type: "POST",
                data: fd,
                processData: false,
                contentType: false,
                beforeSend: () => {
                    $(".preloader").addClass("preloader_active");
                },
                success: function success(res) {
                    $(".preloader").removeClass("preloader_active");

                    // Посмотреть на статус ответа, если ошибка
                    console.log(res);


                    if (res['message'] === "SUCCESS") {
                        message.text(successSendText).css("color", "#21d4bb");
                        setTimeout(() => {
                            message.text("");
                        }, 4000);
                    } else if (res['message'] === "NOTVALID") {
                        message.text(requiredFieldsText).css("color", "#d42121");
                        setTimeout(() => {
                            message.text("");
                        }, 3000);
                    } else {
                        message.text(errorSendText).css("color", "#d42121");
                        setTimeout(() => {
                            message.text("");
                        }, 4000);
                    }
                }
            });
        });
    })(jQuery);

    let header = $("#header");
    let intro = $("#intro");
    let introH = intro.innerHeight();
    let scrollPos = $(window).scrollTop();

    checkScroll(scrollPos,introH);
    $(window).on("scroll", function (){
        scrollPos = $(this).scrollTop();
        checkScroll(scrollPos,introH);


    });
    function checkScroll(scrollPos,introH){
        if(scrollPos > introH){
            header.addClass("fixed")
        }else {
            header.removeClass("fixed")
        }

    }

    /*Scroll */

    $("[data-scroll]").on("click", function (event){
        event.preventDefault();

        let elementID = $(this).data('scroll');
        let elementOffset = $(elementID).offset().top;


        $("html, body").animate({
            scrollTop:elementOffset - 85
        }, 700);


    });
    /* Slider https://kenwheeler.github.io/slick/ */
    let slider = $("#reviewSlider");
    slider.slick({
        infinite: true,
        slidesToShow: 1,
        slidesToScroll: 1,
        fade: true,
        arrows: false,
        dots: true
    });

});

