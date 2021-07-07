(function($) {
  'use strict';
  let editor = ace.edit("aceExample");
  editor.setTheme("ace/theme/chaos");
  editor.getSession().setMode("ace/mode/javascript");
  document.getElementById('aceExample').style.fontSize = '1rem';
})(jQuery);