var current_href;

moment.lang('zh-CN');

current_href = location.pathname.split("/")[1] || 'index';

$('.navbar a[href^="' + current_href + '"]').parent().addClass('active');

$('.date').each(function() {
  var date;
  date = $(this).text();
  return $(this).attr('title', date).text(moment(date).fromNow());
});

$('.wysiwyg').summernote({
  lang: 'zh-CN',
  minHeight: 600,
  toolbar: [['style', ['style', 'bold', 'italic', 'clear']], ['insert', ['link', 'picture']], ['layout', ['ul', 'ol']], ['misc', ['codeview', 'fullscreen']]]
});

$('form').submit(function() {
  return $('.wysiwyg').each(function() {
    return $(this).val($(this).code());
  });
});

$('[checked="false"]').removeAttr('checked');

$('.checkbox.switch').bootstrapSwitch();
