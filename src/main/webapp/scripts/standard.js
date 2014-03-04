function confirmDelete(delUrl) {
  if (confirm("Are you sure ?")) {
    document.location = delUrl;
  }
}

var UserHelper = function () {
  this.updateUserToRole = function (url, select) {
    console.log(url, select);
    var block = $(select);
    var btn = block.find("button");
    if (btn.length > 0) {
      btn.on("click",function(e) {
        var $this   = $(this);
        var inputs = block.find("input");
        var roles = [];
        inputs.each(function(i, elem) {
          var $elem = $(elem);
          if ((typeof elem.checked != "undefined" && elem.checked)) {
            //roles[$elem.val()] = true;
            roles.push($elem.val());
          }
        });
        $.ajax({
          url: url,
          type: "POST",
          //data: {roles: JSON.stringify(roles)},
          data: {roles: roles.join(';')},
          success: function(data) {
            alert("Success");
            console.log(data);
          },
          error: function(data, object) {
            alert("Error");
            if (typeof data.responseText != "undefined") {
              var errorBlock = $('#errorBlock');
              var text = $(data.responseText).text();
              errorBlock.find(".modal-body").html(text);
                errorBlock.modal('toggle', this)
                .one('hide', function () {
                  $this.is(':visible') && $this.focus()
                });
            }
            console.log(data);
            console.log(object);
          }
        });
        return false;
      });
    }
  }
};
UserHelper = new UserHelper();