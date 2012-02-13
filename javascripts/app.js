/* Foundation v2.1.5 http://foundation.zurb.com */
$(document).ready(function () {
	function showStep(a) {
		// Store references
		var $a = $(a),
			  id = $a.attr('href').replace('#','');

    // Locate elements
    var $li       = $a.parent('li');
    var $content  = $('#' + id);

    // Hide/clear all others
    $('.steps li.active').removeClass('active');
    $('.guide .content.active').slideUp(function() { $(this).removeClass('active'); });
    
    // Show the current items
    $li.addClass('active');
    $content.slideDown(function() { $(this).addClass('active'); });
	}
	$('.steps li a')
		.on('click', function() {
			showStep(this, false);	
		})
});
