/*
 * jQuery UI Window 1.8.16
 * w/ Minimize & Maximize Support
 * by Elijah Horton (fieryprophet@yahoo.com)
 * modified by Fabiano Fonseca de Freitas
 *
 * http://docs.jquery.com/UI/Window
 *
 * Depends:
 *	jquery.ui.core.js
 *	jquery.ui.widget.js
 *  jquery.ui.button.js
 *	jquery.ui.draggable.js
 *	jquery.ui.mouse.js
 *	jquery.ui.position.js
 *	jquery.ui.resizable.js
 *	BrowserDetect.js
 */
(function( $, undefined ) {

var uiWindowClasses =
		'ui-window ' +
		'ui-widget ' +
		'ui-widget-content ' +
		'ui-corner-all ',
	sizeRelatedOptions = {
		buttons: true,
		height: true,
		maxHeight: true,
		maxWidth: true,
		minHeight: true,
		minWidth: true,
		width: true
	},
	resizableRelatedOptions = {
		maxHeight: true,
		maxWidth: true,
		minHeight: true,
		minWidth: true
	},
	// support for jQuery 1.3.2 - handle common attrFn methods for window
	attrFn = $.attrFn || {
		val: true,
		css: true,
		html: true,
		text: true,
		data: true,
		width: true,
		height: true,
		offset: true,
		click: true
	};

$.widget("ui.window", {
	options: {
		autoOpen: true,
		buttons: {},
		closeOnEscape: true,
		closeText: 'close',
		windowClass: '',
		draggable: true,
		hide: null,
		height: 'auto',
		maxHeight: false,
		maxWidth: false,
		minHeight: 150,
		minWidth: 280,
		minimizeText: 'minimize',
		maximizeText: 'maximize',
		minimize: true,
		maximize: true,
		modal: false,
		position: {
			my: 'center',
			at: 'center',
			collision: 'fit',
			// ensure that the titlebar is never outside the document
			using: function(pos) {
				var topOffset = $(this).css(pos).offset().top;
				if (topOffset < 0) {
					$(this).css('top', pos.top - topOffset);
				}
			}
		},
		resizable: true,
		show: null,
		stack: true,
		title: '',
		width: 300,
		zIndex: 1000,
		itemToolbarMinimizedClass: '',
		containment: 'document'
	},

	_create: function() {
		this.originalTitle = this.element.attr('title');
		// #5742 - .attr() might return a DOMElement
		if ( typeof this.originalTitle !== "string" ) {
			this.originalTitle = "";
		}

		this.options.title = this.options.title || this.originalTitle;
		var self = this,
			options = self.options,

			title = options.title || '&#160;',
			titleId = $.ui.window.getTitleId(self.element),

			uiWindow = (self.uiWindow = $('<div></div>'))
				.appendTo(document.body)
				.hide()
				.addClass(uiWindowClasses + options.windowClass)
				.css({
					zIndex: options.zIndex
				})
				// setting tabIndex makes the div focusable
				// setting outline to 0 prevents a border on focus in Mozilla
				.attr('tabIndex', -1).css('outline', 0).keydown(function(event) {
					if (options.closeOnEscape && !event.isDefaultPrevented() && event.keyCode &&
						event.keyCode === $.ui.keyCode.ESCAPE) {
						
						self.close(event);
						event.preventDefault();
					}
				})
				.attr({
					role: 'window',
					'aria-labelledby': titleId
				})
				.mousedown(function(event) {
					self.moveToTop(false, event);
				}),

			uiWindowContent = self.element
				.show()
				.removeAttr('title')
				.addClass(
					'ui-window-content ' +
					'ui-widget-content')
				.appendTo(uiWindow),

			uiWindowTitlebar = (self.uiWindowTitlebar = $('<div></div>'))
				.addClass(
					'ui-window-titlebar ' +
					'ui-widget-header ' +
					'ui-corner-all ' +
					'ui-helper-clearfix'
				)
				.prependTo(uiWindow);
			if(options.close !== false){
				var uiWindowTitlebarClose = $('<a href="#"></a>')
					.addClass(
						'ui-window-titlebar-close ' +
						'ui-corner-all'
					)
					.attr('role', 'button')
					.hover(
						function() {
							uiWindowTitlebarClose.addClass('ui-state-hover');
						},
						function() {
							uiWindowTitlebarClose.removeClass('ui-state-hover');
						}
					)
					.focus(function() {
						uiWindowTitlebarClose.addClass('ui-state-focus');
					})
					.blur(function() {
						uiWindowTitlebarClose.removeClass('ui-state-focus');
					})
					.click(function(event) {
						self.close(event);
						return false;
					})
					.appendTo(uiWindowTitlebar),
	
				uiWindowTitlebarCloseText = (self.uiWindowTitlebarCloseText = $('<span></span>'))
					.addClass(
						'ui-icon ' +
						'ui-icon-closethick'
					)
					.text(options.closeText)
					.appendTo(uiWindowTitlebarClose);
			}
			if(options.maximize && !options.modal){ //cannot use this option with modal
				var uiWindowTitlebarMaximize = $('<a href="#"></a>')
					.addClass(
						'ui-window-titlebar-maximize ' +
						'ui-corner-all'
					)
					.attr('role', 'button')
					.hover(
						function() {
							uiWindowTitlebarMaximize.addClass('ui-state-hover');
						},
						function() {
							uiWindowTitlebarMaximize.removeClass('ui-state-hover');
						}
					)
					.focus(function() {
						uiWindowTitlebarMaximize.addClass('ui-state-focus');
					})
					.blur(function() {
						uiWindowTitlebarMaximize.removeClass('ui-state-focus');
					})
					.click(function(event) {
						self.maximize(event);
						return false;
					})
					.appendTo(uiWindowTitlebar),
	
				uiWindowTitlebarMaximizeText = (self.uiWindowTitlebarMaximizeText = $('<span></span>'))
					.addClass(
						'ui-icon ' +
						'ui-icon-plusthick'
					)
					.text(options.maximizeText)
					.appendTo(uiWindowTitlebarMaximize);
					$(uiWindowTitlebar).dblclick(function(event) {
						self.maximize(event);
						return false;
					});
			}
			if(options.minimize && !options.modal){ //cannot use this option with modal
				var uiWindowTitlebarMinimize = $('<a href="#"></a>')
					.addClass(
						'ui-window-titlebar-minimize ' +
						'ui-corner-all'
					)
					.attr('role', 'button')
					.hover(
						function() {
							uiWindowTitlebarMinimize.addClass('ui-state-hover');
						},
						function() {
							uiWindowTitlebarMinimize.removeClass('ui-state-hover');
						}
					)
					.focus(function() {
						uiWindowTitlebarMinimize.addClass('ui-state-focus');
					})
					.blur(function() {
						uiWindowTitlebarMinimize.removeClass('ui-state-focus');
					})
					.click(function(event) {
						self.minimize(event);
						return false;
					})
					.appendTo(uiWindowTitlebar),
	
				uiWindowTitlebarMinimizeText = (self.uiWindowTitlebarMinimizeText = $('<span></span>'))
					.addClass(
						'ui-icon ' +
						'ui-icon-minusthick'
					)
					.text(options.minimizeText)
					.appendTo(uiWindowTitlebarMinimize);
			}

			uiWindowTitle = $('<span></span>')
				.addClass('ui-window-title')
				.attr('id', titleId)
				.html(title)
				.prependTo(uiWindowTitlebar);

		//handling of deprecated beforeclose (vs beforeClose) option
		//Ticket #4669 http://dev.jqueryui.com/ticket/4669
		//TODO: remove in 1.9pre
		if ($.isFunction(options.beforeclose) && !$.isFunction(options.beforeClose)) {
			options.beforeClose = options.beforeclose;
		}

		uiWindowTitlebar.find("*").add(uiWindowTitlebar).disableSelection();

		if (options.draggable && $.fn.draggable) {
			self._makeDraggable();
		}
		if (options.resizable && $.fn.resizable) {
			self._makeResizable();
		}

		self._createButtons(options.buttons);
		self._isOpen = false;
		self._min = false;

		if ($.fn.bgiframe) {
			uiWindow.bgiframe();
		}
	},

	_init: function() {
		if ( this.options.autoOpen ) {
			this.open();
		}
	},

	destroy: function() {
		var self = this;
		
		if (self.overlay) {
			self.overlay.destroy();
		}
		self.uiWindow.hide();
		self.element
			.unbind('.window')
			.removeData('window')
			.removeClass('ui-window-content ui-widget-content')
			.hide().appendTo('body');
		self.uiWindow.remove();

		if (self.originalTitle) {
			self.element.attr('title', self.originalTitle);
		}

		return self;
	},

	widget: function() {
		return this.uiWindow;
	},

	minimize: function(event) {
		var self = this,
			ui = self.uiWindow;
		if(false === self._trigger('beforeMinimize', event)) {
			return;
		}
		if(!ui.data('is-minimized')){
			if(self.options.minimize && typeof self.options.minimize !== "boolean" && $(self.options.minimize).length > 0){
				/*self._min = $('<a>' + (ui.find('span.ui-window-title').html().replace(/&nbsp;/, '') || 'Untitled Window') + '</a>')
					.attr('title', 'Click to restore window').addClass('ui-corner-all ui-button').click(function(event){self.unminimize(event);});*/
				self._min = $('<a>' + (ui.find('span.ui-window-title').html().replace(/&nbsp;/, '') || 'Untitled Window') + '</a>')
				.attr('title', 'Click to restore window').addClass(self.options.itemToolbarMinimizedClass).click(function(event){self.unminimize(event);});
				$(self.options.minimize).append(self._min);
				ui.data('is-minimized', true).hide();
			} else {
				if(ui.is( ":data(resizable)" )) {
					ui.data('was-resizable', true).resizable('destroy');
				} else {
					ui.data('was-resizable', false)
				}
				ui.data('minimized-height', ui.height());
				ui.find('.ui-window-content').hide();
				ui.find('.ui-window-titlebar-maximize').hide();
				ui.find('.ui-window-titlebar-minimize').css('right', '1.8em').removeClass('ui-icon-minusthick').addClass('ui-icon-arrowthickstop-1-s')
					.find('span').removeClass('ui-icon-minusthick').addClass('ui-icon-arrowthickstop-1-s').click(function(event){self.unminimize(event); return false;});;
				ui.data('is-minimized', true).height('auto');
			}
		}
		return self;
	},
	
	unminimize: function(event) {
		var self = this,
			ui = self.uiWindow;
		if(false === self._trigger('beforeUnminimize', event)) {
			return;
		}
		if(ui.data('is-minimized')){
			if(self._min){
				self._min.unbind().remove();
				self._min = false;
				ui.data('is-minimized', false).show();
				self.moveToTop();
			} else {
				ui.height(ui.data('minimized-height')).data('is-minimized', false).removeData('minimized-height').find('.ui-window-content').show();
				ui.find('.ui-window-titlebar-maximize').show();
				ui.find('.ui-window-titlebar-minimize').css('right', '3.3em').removeClass('ui-icon-arrowthickstop-1-s').addClass('ui-icon-minusthick')
					.find('span').removeClass('ui-icon-arrowthickstop-1-s').addClass('ui-icon-minusthick').click(function(event){self.minimize(event); return false;});
				if(ui.data('was-resizable') == true) {
					self._makeResizable(true);
				}
			}
		}
		return self;
	},

	maximize: function(event) {
		var self = this,
			ui = self.uiWindow;
		
		var maximizeTo = window;
		var top = 0;
		var left = 0;
		
		if(self.options.maximizeTo != ''){
			var content = $(self.options.containment)[0];
			if(content){
				maximizeTo = content;
				var offset = $(content).offset();
				top = offset.top;
				left = offset.left;
			}
		}
		
		if(false === self._trigger('beforeMaximize', event)) {
			return;
		}
		if(!ui.data('is-maximized')){
			if(ui.is( ":data(draggable)" )) {
				ui.data('was-draggable', true).draggable('destroy');
			} else {
				ui.data('was-draggable', false)
			}
			if(ui.is( ":data(resizable)" )) {
				ui.data('was-resizable', true).resizable('destroy');
			} else {
				ui.data('was-resizable', false)
			}
			ui.data('maximized-height', ui.height())
				.data('maximized-width', ui.width())
				.data('maximized-top', ui.css('top'))
				.data('maximized-left', ui.css('left'))
				.data('is-maximized', true)
				.height($(maximizeTo).height())
				.width($(maximizeTo).width())
				.css({"top":top, "left": left});
			
			ui.find('.ui-window-content').height($(maximizeTo).height() - 30).css({width: 'auto'});
			
			ui.find('.ui-window-titlebar-maximize').find('span').click(function(event){self.unmaximize(event); return false;});
			
			ui.find('.ui-window-titlebar').dblclick(function(event){self.unmaximize(event); return false;});
		}
		return self;
	},
	
	unmaximize: function(event) {
		var self = this,
			ui = self.uiWindow;
		
		if(false === self._trigger('beforeUnmaximize', event)) {
			return;
		}
		if(ui.data('is-maximized')){
			ui.height(ui.data('maximized-height')).width(ui.data('maximized-width')).css({"top":ui.data('maximized-top'), "left":ui.data('maximized-left')})
				.data('is-maximized', false).removeData('maximized-height').removeData('maximized-width').removeData('maximized-top').removeData('maximized-left').find('.ui-window-titlebar-minimize').show();
			ui.find('.ui-window-titlebar-maximize').removeClass('ui-icon-arrowthick-1-sw').addClass('ui-icon-plusthick')
				.find('span').removeClass('ui-icon-arrowthick-1-sw').addClass('ui-icon-plusthick').click(function(){self.maximize(event); return false;});
			ui.find('.ui-window-titlebar').dblclick(function(event){self.maximize(event); return false;});
			if(ui.data('was-draggable') == true) {
				self._makeDraggable(true);
			}
			if(ui.data('was-resizable') == true) {
				self._makeResizable(true);
			}
			
			self._size();
		}
		return self;
	},

	close: function(event) {
		var self = this,
			maxZ, thisZ;
		
		if (false === self._trigger('beforeClose', event)) {
			return;
		}
		if (self.overlay) {
			self.overlay.destroy();
		}
		self.uiWindow.unbind('keypress.ui-window');
		//destroy all richtext field instances because ckeditor is retarded
		self.uiWindow.find('.bm-element-richtext').each(function(){$(this).ckeditorGet().destroy();})

		self._isOpen = false;

		if (self.options.hide) {
			self.uiWindow.hide(self.options.hide, function() {
				self._trigger('close', event);
			});
		} else {
			self.uiWindow.remove();
			self.element.remove();
			self._trigger('close', event);
		}

		$.ui.window.overlay.resize();

		// adjust the maxZ to allow other modal windows to continue to work (see #4309)
		if (self.options.modal) {
			maxZ = 0;
			$('.ui-window').each(function() {
				if (this !== self.uiWindow[0]) {
					thisZ = $(this).css('z-index');
					if(!isNaN(thisZ)) {
						maxZ = Math.max(maxZ, thisZ);
					}
				}
			});
			$.ui.window.maxZ = maxZ;
		}
		return self;
	},

	isOpen: function() {
		return this._isOpen;
	},

	// the force parameter allows us to move modal windows to their correct
	// position on open
	moveToTop: function(force, event) {
		var self = this,
			options = self.options,
			saveScroll;

		if ((options.modal && !force) ||
			(!options.stack && !options.modal)) {
			return self._trigger('focus', event);
		}

		if (options.zIndex > $.ui.window.maxZ) {
			$.ui.window.maxZ = options.zIndex;
		}
		if (self.overlay) {
			$.ui.window.maxZ += 1;
			self.overlay.$el.css('z-index', $.ui.window.overlay.maxZ = $.ui.window.maxZ);
		}

		//Save and then restore scroll since Opera 9.5+ resets when parent z-Index is changed.
		//  http://ui.jquery.com/bugs/ticket/3193
		saveScroll = { scrollTop: self.element.scrollTop(), scrollLeft: self.element.scrollLeft() };
		$.ui.window.maxZ += 1;
		self.uiWindow.css('z-index', $.ui.window.maxZ);
		self.element.attr(saveScroll);
		self._trigger('focus', event);

		return self;
	},

	open: function() {
		if (this._isOpen) { return; }

		var self = this,
			options = self.options,
			uiWindow = self.uiWindow;

		self.overlay = options.modal ? new $.ui.window.overlay(self) : null;
		self._size();
		self._position(options.position);
		uiWindow.show(options.show);
		self.moveToTop(true);

		// prevent tabbing out of modal windows
		if (options.modal) {
			uiWindow.bind('keypress.ui-window', function(event) {
				if (event.keyCode !== $.ui.keyCode.TAB) {
					return;
				}

				var tabbables = $(':tabbable', this),
					first = tabbables.filter(':first'),
					last  = tabbables.filter(':last');

				if (event.target === last[0] && !event.shiftKey) {
					first.focus(1);
					return false;
				} else if (event.target === first[0] && event.shiftKey) {
					last.focus(1);
					return false;
				}
			});
		}

		// set focus to the first tabbable element in the content area or the first button
		// if there are no tabbable elements, set focus on the window itself
		/* What retarded monkey thought this behavior was a good idea? Disable the stupid autofocus --
		$(self.element.find(':tabbable').get().concat(
			uiWindow.find('.ui-window-buttonpane :tabbable').get().concat(
				uiWindow.get()))).eq(0).focus();
		-- Focus window instead */
		uiWindow.focus();

		self._isOpen = true;
		self._trigger('open');

		return self;
	},

	_createButtons: function(buttons) {
		var self = this,
			hasButtons = false,
			uiWindowButtonPane = $('<div></div>')
				.addClass(
					'ui-window-buttonpane ' +
					'ui-widget-content ' +
					'ui-helper-clearfix'
				),
			uiButtonSet = $( "<div></div>" )
				.addClass( "ui-window-buttonset" )
				.appendTo( uiWindowButtonPane );

		// if we already have a button pane, remove it
		self.uiWindow.find('.ui-window-buttonpane').remove();

		if (typeof buttons === 'object' && buttons !== null) {
			$.each(buttons, function() {
				return !(hasButtons = true);
			});
		}
		if (hasButtons) {
			$.each(buttons, function(name, props) {
				props = $.isFunction( props ) ?
					{ click: props, text: name } :
					props;
				var button = $('<button type="button"></button>')
					.click(function() {
						props.click.apply(self.element[0], arguments);
					})
					.appendTo(uiButtonSet);
				// can't use .attr( props, true ) with jQuery 1.3.2.
				$.each( props, function( key, value ) {
					if ( key === "click" ) {
						return;
					}
					if ( key in attrFn ) {
						button[ key ]( value );
					} else {
						button.attr( key, value );
					}
				});
				if ($.fn.button) {
					button.button();
				}
			});
			uiWindowButtonPane.appendTo(self.uiWindow);
		}
	},

	_makeDraggable: function() {
		var self = this,
			options = self.options,
			doc = $(document),
			heightBeforeDrag;

		function filteredUi(ui) {
			return {
				position: ui.position,
				offset: ui.offset
			};
		}
		
		self.uiWindow.draggable({
			cancel: '.ui-window-content, .ui-window-titlebar-close',
			handle: '.ui-window-titlebar',
			containment: options.containment,
			start: function(event, ui) {
				heightBeforeDrag = options.height === "auto" ? "auto" : $(this).height();
				$(this).height($(this).height()).addClass("ui-window-dragging");
				self._trigger('dragStart', event, filteredUi(ui));
			},
			drag: function(event, ui) {
				self._trigger('drag', event, filteredUi(ui));
			},
			stop: function(event, ui) {
				options.position = [ui.position.left - doc.scrollLeft(),
					ui.position.top - doc.scrollTop()];
				$(this).removeClass("ui-window-dragging").height(heightBeforeDrag);
				self._trigger('dragStop', event, filteredUi(ui));
				$.ui.window.overlay.resize();
			}
		});
	},

	_makeResizable: function(handles) {
		handles = (handles === undefined ? this.options.resizable : handles);
		var self = this,
			options = self.options,
			// .ui-resizable has position: relative defined in the stylesheet
			// but windows have to use absolute or fixed positioning
			position = self.uiWindow.css('position'),
			resizeHandles = (typeof handles === 'string' ?
				handles	:
				'n,e,s,w,se,sw,ne,nw'
			);

		function filteredUi(ui) {
			return {
				originalPosition: ui.originalPosition,
				originalSize: ui.originalSize,
				position: ui.position,
				size: ui.size
			};
		}
		self.uiWindow.resizable({
			cancel: '.ui-window-content',
			containment: 'document',
			alsoResize: self.element,
			maxWidth: options.maxWidth,
			maxHeight: options.maxHeight,
			minWidth: options.minWidth,
			minHeight: self._minHeight(),
			handles: resizeHandles,
			start: function(event, ui) {
				//Correção para que ui-window-content receba altura e largura desconsiderando a borda da janela
				ui.originalSize.width = ui.originalSize.width + 2;
				ui.originalSize.height = ui.originalSize.height + 2;
				
				$(this).addClass("ui-window-resizing");
				self._trigger('resizeStart', event, filteredUi(ui));
			},
			resize: function(event, ui){
				//Correção para que ui-window-content receba altura e largura desconsiderando a borda da janela
				/*if((ui.originalSize.width-2) <= ui.size.width)
					ui.originalSize.width = ui.originalSize.width - 2;
				if((ui.originalSize.height-2) <= ui.size.height)
					ui.originalSize.height = ui.originalSize.height - 2;*/
				
				self._trigger('resize', event, filteredUi(ui));
			},
			stop: function(event, ui) {
				$(this).removeClass("ui-window-resizing");
				options.height = $(this).height();
				options.width = $(this).width();
				self._trigger('resizeStop', event, filteredUi(ui));
				$.ui.window.overlay.resize();
			}
		})
		.css('position', position)
		.find('.ui-resizable-se').addClass('ui-icon ui-icon-grip-diagonal-se');
	},

	_minHeight: function() {
		var options = this.options;

		if (options.height === 'auto') {
			return options.minHeight;
		} else {
			return Math.min(options.minHeight, options.height);
		}
	},

	_position: function(position) {
		var myAt = [],
			offset = [0, 0],
			isVisible;

		if (position) {
			// deep extending converts arrays to objects in jQuery <= 1.3.2 :-(
	//		if (typeof position == 'string' || $.isArray(position)) {
	//			myAt = $.isArray(position) ? position : position.split(' ');

			if (typeof position === 'string' || (typeof position === 'object' && '0' in position)) {
				myAt = position.split ? position.split(' ') : [position[0], position[1]];
				if (myAt.length === 1) {
					myAt[1] = myAt[0];
				}

				$.each(['left', 'top'], function(i, offsetPosition) {
					if (+myAt[i] === myAt[i]) {
						offset[i] = myAt[i];
						myAt[i] = offsetPosition;
					}
				});

				position = {
					my: myAt.join(" "),
					at: myAt.join(" "),
					offset: offset.join(" ")
				};
			} 

			position = $.extend({}, $.ui.window.prototype.options.position, position);
		} else {
			position = $.ui.window.prototype.options.position;
		}

		// need to show the window to get the actual offset in the position plugin
		isVisible = this.uiWindow.is(':visible');
		if (!isVisible) {
			this.uiWindow.show();
		}
		this.uiWindow
			// workaround for jQuery bug #5781 http://dev.jquery.com/ticket/5781
			.css({ top: 0, left: 0 })
			.position($.extend({ of: window }, position));
		if (!isVisible) {
			this.uiWindow.hide();
		}
	},

	_setOptions: function( options ) {
		var self = this,
			resizableOptions = {},
			resize = false;

		$.each( options, function( key, value ) {
			self._setOption( key, value );
			
			if ( key in sizeRelatedOptions ) {
				resize = true;
			}
			if ( key in resizableRelatedOptions ) {
				resizableOptions[ key ] = value;
			}
		});

		if ( resize ) {
			this._size();
		}
		if ( this.uiWindow.is( ":data(resizable)" ) ) {
			this.uiWindow.resizable( "option", resizableOptions );
		}
	},

	_setOption: function(key, value){
		var self = this,
			uiWindow = self.uiWindow;

		switch (key) {
			//handling of deprecated beforeclose (vs beforeClose) option
			//Ticket #4669 http://dev.jqueryui.com/ticket/4669
			//TODO: remove in 1.9pre
			case "beforeclose":
				key = "beforeClose";
				break;
			case "buttons":
				self._createButtons(value);
				break;
			case "closeText":
				// ensure that we always pass a string
				self.uiWindowTitlebarCloseText.text("" + value);
				break;
			case "windowClass":
				uiWindow
					.removeClass(self.options.windowClass)
					.addClass(uiWindowClasses + value);
				break;
			case "disabled":
				if (value) {
					uiWindow.addClass('ui-window-disabled');
				} else {
					uiWindow.removeClass('ui-window-disabled');
				}
				break;
			case "draggable":
				var isDraggable = uiWindow.is( ":data(draggable)" );
				if ( isDraggable && !value ) {
					uiWindow.draggable( "destroy" );
				}
				
				if ( !isDraggable && value ) {
					self._makeDraggable();
				}
				break;
			case "position":
				self._position(value);
				break;
			case "resizable":
				// currently resizable, becoming non-resizable
				var isResizable = uiWindow.is( ":data(resizable)" );
				if (isResizable && !value) {
					uiWindow.resizable('destroy');
				}

				// currently resizable, changing handles
				if (isResizable && typeof value === 'string') {
					uiWindow.resizable('option', 'handles', value);
				}

				// currently non-resizable, becoming resizable
				if (!isResizable && value !== false) {
					self._makeResizable(value);
				}
				break;
			case "title":
				// convert whatever was passed in o a string, for html() to not throw up
				$(".ui-window-title", self.uiWindowTitlebar).html("" + (value || '&#160;'));
				break;
		}

		$.Widget.prototype._setOption.apply(self, arguments);
	},

	_size: function() {
		/* If the user has resized the window, the .ui-window and .ui-window-content
		 * divs will both have width and height set, so we need to reset them
		 */
		var options = this.options,
			nonContentHeight,
			minContentHeight,
			isVisible = this.uiWindow.is( ":visible" );

		// reset content sizing
		this.element.show().css({
			width: 'auto',
			minHeight: 0,
			height: 0
		});

		if (options.minWidth > options.width) {
			options.width = options.minWidth;
		}

		// reset wrapper sizing
		// determine the height of all the non-content elements
		nonContentHeight = this.uiWindow.css({
				height: 'auto',
				width: options.width
			})
			.height();
		minContentHeight = Math.max( 0, options.minHeight - nonContentHeight );
		
		if ( options.height === "auto" ) {
			// only needed for IE6 support
			if ( $.support.minHeight ) {
				this.element.css({
					minHeight: minContentHeight,
					height: "auto"
				});
			} else {
				this.uiWindow.show();
				var autoHeight = this.element.css( "height", "auto" ).height();
				if ( !isVisible ) {
					this.uiWindow.hide();
				}
				this.element.height( Math.max( autoHeight, minContentHeight ) );
			}
		} else {
			this.element.height( Math.max( options.height - nonContentHeight, 0 ) );
		}

		if (this.uiWindow.is(':data(resizable)')) {
			this.uiWindow.resizable('option', 'minHeight', this._minHeight());
		}
	}
});

$.extend($.ui.window, {
	version: "1.8.16",

	uuid: 0,
	maxZ: 2500,

	getTitleId: function($el) {
		var id = $el.attr('id');
		if (!id) {
			this.uuid += 1;
			id = this.uuid;
		}
		return 'ui-window-title-' + id;
	},

	overlay: function(window) {
		this.$el = $.ui.window.overlay.create(window);
	}
});

$.extend($.ui.window.overlay, {
	instances: [],
	// reuse old instances due to IE memory leak with alpha transparency (see #5185)
	oldInstances: [],
	maxZ: 0,
	events: $.map('focus,mousedown,mouseup,keydown,keypress,click'.split(','),
		function(event) { return event + '.window-overlay'; }).join(' '),
	create: function(window) {
		if (this.instances.length === 0) {
			// prevent use of anchors and inputs
			// we use a setTimeout in case the overlay is created from an
			// event that we're going to be cancelling (see #2804)
			setTimeout(function() {
				// handle $(el).window().window('close') (see #4065)
				if ($.ui.window.overlay.instances.length) {
					$(document).bind($.ui.window.overlay.events, function(event) {
						// stop events if the z-index of the target is < the z-index of the overlay
						// we cannot return true when we don't want to cancel the event (#3523)
						if ($(event.target).zIndex() < $.ui.window.overlay.maxZ) {
							return false;
						}
					});
				}
			}, 1);

			// allow closing by pressing the escape key
			$(document).bind('keydown.window-overlay', function(event) {
				if (window.options.closeOnEscape && !event.isDefaultPrevented() && event.keyCode &&
					event.keyCode === $.ui.keyCode.ESCAPE) {
					
					window.close(event);
					event.preventDefault();
				}
			});

			// handle window resize
			$(window).bind('resize.window-overlay', $.ui.window.overlay.resize);
		}

		var $el = (this.oldInstances.pop() || $('<div></div>').addClass('ui-widget-overlay'))
			.appendTo(document.body)
			.css({
				width: this.width(),
				height: this.height()
			});

		if ($.fn.bgiframe) {
			$el.bgiframe();
		}

		this.instances.push($el);
		return $el;
	},

	destroy: function($el) {
		var indexOf = $.inArray($el, this.instances);
		if (indexOf != -1){
			this.oldInstances.push(this.instances.splice(indexOf, 1)[0]);
		}

		if (this.instances.length === 0) {
			$([document, window]).unbind('.window-overlay');
		}

		$el.remove();
		
		// adjust the maxZ to allow other modal windows to continue to work (see #4309)
		var maxZ = 0;
		$.each(this.instances, function() {
			maxZ = Math.max(maxZ, this.css('z-index'));
		});
		this.maxZ = maxZ;
	},

	height: function() {
		var scrollHeight,
			offsetHeight;
		// handle IE 6
		if (BrowserDetect.browser == "Explorer" && BrowserDetect.version < 7) {
			scrollHeight = Math.max(
				document.documentElement.scrollHeight,
				document.body.scrollHeight
			);
			offsetHeight = Math.max(
				document.documentElement.offsetHeight,
				document.body.offsetHeight
			);

			if (scrollHeight < offsetHeight) {
				return $(window).height() + 'px';
			} else {
				return scrollHeight + 'px';
			}
		// handle "good" browsers
		} else {
			return $(document).height() + 'px';
		}
	},

	width: function() {
		var scrollWidth,
			offsetWidth;
		// handle IE
		if (BrowserDetect.browser == "Explorer" ) {
			scrollWidth = Math.max(
				document.documentElement.scrollWidth,
				document.body.scrollWidth
			);
			offsetWidth = Math.max(
				document.documentElement.offsetWidth,
				document.body.offsetWidth
			);

			if (scrollWidth < offsetWidth) {
				return $(window).width() + 'px';
			} else {
				return scrollWidth + 'px';
			}
		// handle "good" browsers
		} else {
			return $(document).width() + 'px';
		}
	},

	resize: function() {
		/* If the window is draggable and the user drags it past the
		 * right edge of the window, the document becomes wider so we
		 * need to stretch the overlay. If the user then drags the
		 * window back to the left, the document will become narrower,
		 * so we need to shrink the overlay to the appropriate size.
		 * This is handled by shrinking the overlay before setting it
		 * to the full document size.
		 */
		var $overlays = $([]);
		$.each($.ui.window.overlay.instances, function() {
			$overlays = $overlays.add(this);
		});

		$overlays.css({
			width: 0,
			height: 0
		}).css({
			width: $.ui.window.overlay.width(),
			height: $.ui.window.overlay.height()
		});
	}
});

$.extend($.ui.window.overlay.prototype, {
	destroy: function() {
		$.ui.window.overlay.destroy(this.$el);
	}
});

}(jQuery));