$(function () {
    "use strict";
    //****************************
    /* Left header Theme Change function Start */
    //****************************
    const $link = $('.theme-color .theme-item .theme-link')
    const $header = $('.topbar .top-navbar .navbar-header')
    $link.on("click", function () {
        const logobgskin = $(this).attr("data-logobg");
        $header.attr("data-logobg", logobgskin);
    });

    //****************************
    /* Top navbar Theme Change function Start */
    //****************************
    const $wrapper = $('#main-wrapper')
    const $navbar = $('.topbar .navbar')
    $link.on("click", function () {
        const navbarbgskin = $(this).attr("data-navbarbg");
        $wrapper.attr("data-navbarbg", navbarbgskin);
        $('.topbar .navbar-collapse').attr("data-navbarbg", navbarbgskin);
        if ($wrapper.attr('data-navbarbg') === 'skin6') {
            // do this
            $navbar.addClass('navbar-light');
            $navbar.removeClass('navbar-dark');
        } else {
            // do that
            $navbar.removeClass('navbar-light');
            $navbar.addClass('navbar-dark');
        }
    });

    if ($wrapper.attr('data-navbarbg') === 'skin6') {
        // do this
        $navbar.addClass('navbar-light');
        $navbar.removeClass('navbar-dark');
    }

    //****************************
    // ManageSidebar Type
    //****************************


    //****************************
    /* Manage sidebar bg color */
    //****************************
    $link.on("click", function () {
        const sidebarbgskin = $(this).attr("data-sidebarbg");
        $('.left-sidebar').attr("data-sidebarbg", sidebarbgskin);
    });

    //****************************
    /* sidebar position */
    //****************************
    $('#sidebar-position').change(function () {
        if ($(this).is(":checked")) {
            $wrapper.attr("data-sidebar-position", 'fixed');
            $header.attr("data-navheader", 'fixed');
        } else {
            $wrapper.attr("data-sidebar-position", 'absolute');
            $header.attr("data-navheader", 'relative');
        }
    });

    //****************************
    /* Header position */
    //****************************
    $('#header-position').change(function () {
        if ($(this).is(":checked")) {
            $wrapper.attr("data-header-position", 'fixed');
        } else {
            $wrapper.attr("data-header-position", 'relative');
        }
    });

    //****************************
    /* sidebar position */
    //****************************
    $('#boxed-layout').change(function () {
        if ($(this).is(":checked")) {
            $wrapper.attr("data-boxed-layout", 'boxed');
        } else {
            $wrapper.attr("data-boxed-layout", 'full');
        }
    });

    //****************************
    /* Header position */
    //****************************
    $('#theme-view').change(function () {
        if ($(this).is(":checked")) {
            $('body').attr("data-theme", 'dark');
        } else {
            $('body').attr("data-theme", 'light');
        }
    });
});