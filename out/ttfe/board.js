// Compiled by ClojureScript 0.0-2156
goog.provide('ttfe.board');
goog.require('cljs.core');
ttfe.board._find_empty_tiles = (function _find_empty_tiles(board){return cljs.core.reduce.call(null,(function (acc,row_n){var row = cljs.core.nth.call(null,board,row_n);return cljs.core.concat.call(null,acc,cljs.core.reduce.call(null,(function (col_acc,col_n){var tile = cljs.core.nth.call(null,row,col_n);if((tile == null))
{return cljs.core.conj.call(null,col_acc,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [row_n,col_n], null));
} else
{return col_acc;
}
}),cljs.core.List.EMPTY,cljs.core.range.call(null,cljs.core.count.call(null,row))));
}),cljs.core.List.EMPTY,cljs.core.range.call(null,cljs.core.count.call(null,board)));
});
ttfe.board.add_tile = (function add_tile(board){var vec__6658 = cljs.core.rand_nth.call(null,ttfe.board._find_empty_tiles.call(null,board));var rand_row = cljs.core.nth.call(null,vec__6658,0,null);var rand_col = cljs.core.nth.call(null,vec__6658,1,null);var new_tile_value = cljs.core.rand_nth.call(null,cljs.core.list(2,2,2,2,2,2,2,2,2,4));return cljs.core.assoc_in.call(null,board,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [rand_row,rand_col], null),new_tile_value);
});
ttfe.board.new_board = (function new_board(){return ttfe.board.add_tile.call(null,new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [null,null,null,null], null),new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [null,null,null,null], null),new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [null,null,null,null], null),new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [null,null,null,null], null)], null));
});
ttfe.board.move_right = (function move_right(board){return cljs.core.mapv.call(null,(function (line){var numbers = cljs.core.filter.call(null,(function (p1__6659_SHARP_){return cljs.core.not_EQ_.call(null,p1__6659_SHARP_,null);
}),line);var rnumbers = cljs.core.reverse.call(null,numbers);var crunched_numbers = (function (){var acc = cljs.core.PersistentVector.EMPTY;var i = 0;while(true){
if((i >= cljs.core.count.call(null,numbers)))
{return acc;
} else
{if(cljs.core._EQ_.call(null,cljs.core.nth.call(null,rnumbers,i),cljs.core.nth.call(null,rnumbers,(i + 1),null)))
{{
var G__6660 = cljs.core.conj.call(null,acc,(2 * cljs.core.nth.call(null,rnumbers,i)));
var G__6661 = (i + 2);
acc = G__6660;
i = G__6661;
continue;
}
} else
{{
var G__6662 = cljs.core.conj.call(null,acc,cljs.core.nth.call(null,rnumbers,i));
var G__6663 = (i + 1);
acc = G__6662;
i = G__6663;
continue;
}
}
}
break;
}
})();return cljs.core.vec.call(null,cljs.core.concat.call(null,cljs.core.repeat.call(null,(cljs.core.count.call(null,line) - cljs.core.count.call(null,crunched_numbers)),null),cljs.core.reverse.call(null,crunched_numbers)));
}),board);
});
ttfe.board.move_left = (function move_left(board){return cljs.core.mapv.call(null,(function (line){var numbers = cljs.core.filter.call(null,(function (p1__6664_SHARP_){return cljs.core.not_EQ_.call(null,p1__6664_SHARP_,null);
}),line);var crunched_numbers = (function (){var acc = cljs.core.PersistentVector.EMPTY;var i = 0;while(true){
if((i >= cljs.core.count.call(null,numbers)))
{return acc;
} else
{if(cljs.core._EQ_.call(null,cljs.core.nth.call(null,numbers,i),cljs.core.nth.call(null,numbers,(i + 1),null)))
{{
var G__6665 = cljs.core.conj.call(null,acc,(2 * cljs.core.nth.call(null,numbers,i)));
var G__6666 = (i + 2);
acc = G__6665;
i = G__6666;
continue;
}
} else
{{
var G__6667 = cljs.core.conj.call(null,acc,cljs.core.nth.call(null,numbers,i));
var G__6668 = (i + 1);
acc = G__6667;
i = G__6668;
continue;
}
}
}
break;
}
})();return cljs.core.vec.call(null,cljs.core.concat.call(null,crunched_numbers,cljs.core.repeat.call(null,(cljs.core.count.call(null,line) - cljs.core.count.call(null,crunched_numbers)),null)));
}),board);
});
ttfe.board._rotate_cw = (function _rotate_cw(board){return cljs.core.apply.call(null,cljs.core.mapv,(function (a,b,c,d){return new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [a,b,c,d], null);
}),cljs.core.vec.call(null,cljs.core.reverse.call(null,board)));
});
ttfe.board._rotate_ccw = (function _rotate_ccw(board){return cljs.core.vec.call(null,cljs.core.reverse.call(null,cljs.core.apply.call(null,cljs.core.mapv,(function (a,b,c,d){return new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [a,b,c,d], null);
}),board)));
});
ttfe.board.move_up = (function move_up(board){return ttfe.board._rotate_ccw.call(null,ttfe.board.move_right.call(null,ttfe.board._rotate_cw.call(null,board)));
});
ttfe.board.move_down = (function move_down(board){return ttfe.board._rotate_ccw.call(null,ttfe.board.move_left.call(null,ttfe.board._rotate_cw.call(null,board)));
});
ttfe.board.movements_left_QMARK_ = (function movements_left_QMARK_(board){var board_moved_left = ttfe.board.move_left.call(null,board);var board_moved_right = ttfe.board.move_right.call(null,board);var board_moved_up = ttfe.board.move_up.call(null,board);var board_moved_down = ttfe.board.move_down.call(null,board);return cljs.core.not_EQ_.call(null,board_moved_left,board_moved_right,board_moved_up,board_moved_down);
});

//# sourceMappingURL=board.js.map