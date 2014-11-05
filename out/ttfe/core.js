// Compiled by ClojureScript 0.0-2156
goog.provide('ttfe.core');
goog.require('cljs.core');
goog.require('ttfe.board');
goog.require('ttfe.board');
goog.require('om.dom');
goog.require('om.dom');
goog.require('om.core');
goog.require('om.core');
cljs.core.enable_console_print_BANG_.call(null);
ttfe.core.new_game = (function new_game(){return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"board","board",1107812952),ttfe.board.new_board.call(null),new cljs.core.Keyword(null,"already-won","already-won",1963129107),false,new cljs.core.Keyword(null,"paused","paused",4313549984),false], null);
});
ttfe.core.app_state = cljs.core.atom.call(null,ttfe.core.new_game.call(null));
ttfe.core.move_fns = new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [ttfe.board.move_up,ttfe.board.move_right,ttfe.board.move_down,ttfe.board.move_left], null);
ttfe.core.divs_for_board = (function divs_for_board(board){return cljs.core.reduce.call(null,(function (row_acc,row_n){var row = cljs.core.nth.call(null,board,row_n);return cljs.core.concat.call(null,row_acc,cljs.core.reduce.call(null,(function (acc,col_n){var cell = cljs.core.nth.call(null,row,col_n);if((cell == null))
{return acc;
} else
{return cljs.core.conj.call(null,acc,React.DOM.div({"className": [cljs.core.str("tile tile-"),cljs.core.str(cell),cljs.core.str(" tile-position-"),cljs.core.str((col_n + 1)),cljs.core.str("-"),cljs.core.str((row_n + 1))].join('')},React.DOM.div({"className": "tile-inner"},cell)));
}
}),cljs.core.PersistentVector.EMPTY,cljs.core.range.call(null,cljs.core.count.call(null,row))));
}),cljs.core.List.EMPTY,cljs.core.range.call(null,cljs.core.count.call(null,board)));
});
ttfe.core.tiles_view = (function tiles_view(app,owner){if(typeof ttfe.core.t6720 !== 'undefined')
{} else
{
/**
* @constructor
*/
ttfe.core.t6720 = (function (owner,app,tiles_view,meta6721){
this.owner = owner;
this.app = app;
this.tiles_view = tiles_view;
this.meta6721 = meta6721;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
ttfe.core.t6720.cljs$lang$type = true;
ttfe.core.t6720.cljs$lang$ctorStr = "ttfe.core/t6720";
ttfe.core.t6720.cljs$lang$ctorPrWriter = (function (this__3970__auto__,writer__3971__auto__,opt__3972__auto__){return cljs.core._write.call(null,writer__3971__auto__,"ttfe.core/t6720");
});
ttfe.core.t6720.prototype.om$core$IRender$ = true;
ttfe.core.t6720.prototype.om$core$IRender$render$arity$1 = (function (_){var self__ = this;
var ___$1 = this;var cell_divs = ttfe.core.divs_for_board.call(null,new cljs.core.Keyword(null,"board","board",1107812952).cljs$core$IFn$_invoke$arity$1(self__.app));return cljs.core.apply.call(null,om.dom.div,{"className": "tile-container", "id": "tiles"},cell_divs);
});
ttfe.core.t6720.prototype.cljs$core$IMeta$_meta$arity$1 = (function (_6722){var self__ = this;
var _6722__$1 = this;return self__.meta6721;
});
ttfe.core.t6720.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (_6722,meta6721__$1){var self__ = this;
var _6722__$1 = this;return (new ttfe.core.t6720(self__.owner,self__.app,self__.tiles_view,meta6721__$1));
});
ttfe.core.__GT_t6720 = (function __GT_t6720(owner__$1,app__$1,tiles_view__$1,meta6721){return (new ttfe.core.t6720(owner__$1,app__$1,tiles_view__$1,meta6721));
});
}
return (new ttfe.core.t6720(owner,app,tiles_view,null));
});
ttfe.core.end_game = (function end_game(class$,message){var msg_cont = document.querySelector(".game-message");msg_cont.classList.remove("game-won");
msg_cont.classList.add(class$);
return msg_cont.getElementsByTagName("p").item(0).textContent = message;
});
ttfe.core.init = (function init(){var input_manager = (new KeyboardInputManager());input_manager.on("move",(function (direction){if(cljs.core.not.call(null,new cljs.core.Keyword(null,"paused","paused",4313549984).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,ttfe.core.app_state))))
{var move_fn = cljs.core.nth.call(null,ttfe.core.move_fns,direction);cljs.core.swap_BANG_.call(null,ttfe.core.app_state,(function (state){var board = new cljs.core.Keyword(null,"board","board",1107812952).cljs$core$IFn$_invoke$arity$1(state);var moved_board = move_fn.call(null,board);if(cljs.core.not_EQ_.call(null,board,moved_board))
{return cljs.core.assoc_in.call(null,state,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"board","board",1107812952)], null),ttfe.board.add_tile.call(null,moved_board));
} else
{return state;
}
}));
if((cljs.core.contains_QMARK_.call(null,cljs.core.set.call(null,cljs.core.flatten.call(null,new cljs.core.Keyword(null,"board","board",1107812952).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,ttfe.core.app_state)))),2048)) && (cljs.core.not.call(null,new cljs.core.Keyword(null,"already-won","already-won",1963129107).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,ttfe.core.app_state)))))
{cljs.core.swap_BANG_.call(null,ttfe.core.app_state,(function (state){return cljs.core.merge.call(null,state,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"already-won","already-won",1963129107),true,new cljs.core.Keyword(null,"paused","paused",4313549984),true], null));
}));
ttfe.core.end_game.call(null,"game-won","You win!");
} else
{}
if(ttfe.board.movements_left_QMARK_.call(null,new cljs.core.Keyword(null,"board","board",1107812952).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,ttfe.core.app_state))))
{return null;
} else
{return ttfe.core.end_game.call(null,"game-over","Game over!");
}
} else
{return null;
}
}));
input_manager.on("restart",(function (){var msg_cont = document.querySelector(".game-message");msg_cont.classList.remove("game-won");
msg_cont.classList.remove("game-over");
return cljs.core.swap_BANG_.call(null,ttfe.core.app_state,(function (state){return ttfe.core.new_game.call(null);
}));
}));
input_manager.on("keepPlaying",(function (){cljs.core.swap_BANG_.call(null,ttfe.core.app_state,(function (state){return cljs.core.assoc_in.call(null,state,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"paused","paused",4313549984)], null),false);
}));
var msg_cont = document.querySelector(".game-message");msg_cont.classList.remove("game-won");
return msg_cont.classList.remove("game-over");
}));
return om.core.root.call(null,ttfe.core.tiles_view,ttfe.core.app_state,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"target","target",4427965699),document.getElementById("tiles")], null));
});

//# sourceMappingURL=core.js.map