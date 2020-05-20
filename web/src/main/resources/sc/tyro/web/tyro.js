(function ($) {
  $.fn.tyro = function (options) {
    switch (options.method) {
      case 'metaInfos':
        let metaInfos = [];
        this.each(function () {
          let me = $(this),
            id = $(this).attr('id');
          if (!id) {
            id = 'gen-' + Math.round(new Date().getTime() * Math.random());
            me.attr('id', id);
          }

          metaInfos.push({
            // See http://learn.jquery.com/using-jquery-core/faq/how-do-i-select-an-element-by-an-id-that-has-characters-used-in-css-notation/
            id: id.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1"),
            node: me.prop('nodeName').toLowerCase()
          });
        });
        return metaInfos;
      case 'contains':
        let el = $("[id='" + options.id + "']");
        let not = [];
        $.each(options.ids, function (index, _id) {
          !$.contains(el[0], $("[id='" + _id + "']")[0]) && not.push(_id);
        });
        return not;
    }
  };
}(window.jQuery));
