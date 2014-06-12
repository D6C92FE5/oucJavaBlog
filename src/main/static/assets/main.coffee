

current_href = location.pathname.split("/")[1] or 'index'
$('.navbar a[href^="' + current_href + '"]').parent().addClass('active')
