

moment.lang('zh-CN')


current_href = location.pathname.split("/")[1] or 'index'
$('.navbar a[href^="' + current_href + '"]').parent().addClass('active')


$('.go-back').click ->
  history.back()


$('.date').each ->
  date = $(this).text()
  $(this).attr('title', date).text(moment(date).fromNow())


$('.wysiwyg').summernote
  lang: 'zh-CN'
  minHeight: 600
  toolbar: [
    ['style', ['style', 'bold', 'italic', 'clear']]
    ['insert', ['link', 'picture']]
    ['layout', ['ul', 'ol']]
    ['misc', ['codeview', 'fullscreen']]
  ]

$('form').submit ->
  $('.wysiwyg').each ->
    $(this).val($(this).code())


$('[checked="false"]').removeAttr('checked')

$('.checkbox.switch').bootstrapSwitch()
