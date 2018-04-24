/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */


$(function(){
    CKEDITOR.replace( 'editor1' );
    CKEDITOR.config.height = 500;
    CKEDITOR.config.width = 'auto';
})

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';

    config.language = 'zh-cn';

    config.toolbar = 'Full';

        config.toolbar_Full = [
           ['Source','-','Save','NewPage','Preview','-','Templates'],
           ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
           ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
           ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
           '/',
           ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
            ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
            ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
            ['Link','Unlink','Anchor'],
           ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
           '/',
            ['Styles','Format','Font','FontSize'],
            ['TextColor','BGColor'],
            ['Maximize','ShowBlocks','-']
        ];

    config.toolbarCanCollapse = true;
    CKEDITOR.config.removeDialogTabs = 'image:advanced;image:Link';
    CKEDITOR.config.filebrowserImageUploadUrl= '/public/uploadCkeditor'

};
