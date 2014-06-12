var current_href;

current_href = location.pathname.split("/")[1] || 'index';

$('.navbar a[href^="' + current_href + '"]').parent().addClass('active');
