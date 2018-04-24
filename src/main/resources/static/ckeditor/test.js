/**
 * Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or https://ckeditor.com/legal/ckeditor-oss-license
 */

/* exported initSample */

if ( CKEDITOR.env.ie && CKEDITOR.env.version < 9 )
	CKEDITOR.tools.enableHtml5Elements( document );

CKEDITOR.config.height = 150;
CKEDITOR.config.width = 'auto';

var initSample = ( function() {
	return function() {
		var editorElement = CKEDITOR.document.getById( 'editor1' );

        CKEDITOR.replace( 'editor1' );

		// Depending on the wysiwygare plugin availability initialize classic or inline editor.
		// if ( wysiwygareaAvailable ) {
		// 	CKEDITOR.replace( 'editor1' );
		// } else {
		// 	editorElement.setAttribute( 'contenteditable', 'true' );
		// 	CKEDITOR.inline( 'editor1' );
        //
		// 	// TODO we can consider displaying some info box that
		// 	// without wysiwygarea the classic editor may not work.
		// }
	};

} )();

