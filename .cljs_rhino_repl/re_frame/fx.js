// Compiled by ClojureScript 1.10.238 {}
goog.provide('re_frame.fx');
goog.require('cljs.core');
goog.require('re_frame.router');
goog.require('re_frame.db');
goog.require('re_frame.interceptor');
goog.require('re_frame.interop');
goog.require('re_frame.events');
goog.require('re_frame.registrar');
goog.require('re_frame.loggers');
goog.require('re_frame.trace');
re_frame.fx.kind = new cljs.core.Keyword(null,"fx","fx",-1237829572);
if(cljs.core.truth_(re_frame.registrar.kinds.call(null,re_frame.fx.kind))){
} else {
throw (new Error("Assert failed: (re-frame.registrar/kinds kind)"));
}
/**
 * Register the given effect `handler` for the given `id`.
 * 
 *   `id` is keyword, often namespaced.
 *   `handler` is a side-effecting function which takes a single argument and whose return
 *   value is ignored.
 * 
 *   Example Use
 *   -----------
 * 
 *   First, registration ... associate `:effect2` with a handler.
 * 
 *   (reg-fx
 *   :effect2
 *   (fn [value]
 *      ... do something side-effect-y))
 * 
 *   Then, later, if an event handler were to return this effects map ...
 * 
 *   {...
 * :effect2  [1 2]}
 * 
 * ... then the `handler` `fn` we registered previously, using `reg-fx`, will be
 * called with an argument of `[1 2]`.
 */
re_frame.fx.reg_fx = (function re_frame$fx$reg_fx(id,handler){
return re_frame.registrar.register_handler.call(null,re_frame.fx.kind,id,handler);
});
/**
 * An interceptor whose `:after` actions the contents of `:effects`. As a result,
 *   this interceptor is Domino 3.
 * 
 *   This interceptor is silently added (by reg-event-db etc) to the front of
 *   interceptor chains for all events.
 * 
 *   For each key in `:effects` (a map), it calls the registered `effects handler`
 *   (see `reg-fx` for registration of effect handlers).
 * 
 *   So, if `:effects` was:
 *    {:dispatch  [:hello 42]
 *     :db        {...}
 *     :undo      "set flag"}
 * 
 *   it will call the registered effect handlers for each of the map's keys:
 *   `:dispatch`, `:undo` and `:db`. When calling each handler, provides the map
 *   value for that key - so in the example above the effect handler for :dispatch
 *   will be given one arg `[:hello 42]`.
 * 
 *   You cannot rely on the ordering in which effects are executed.
 */
re_frame.fx.do_fx = re_frame.interceptor.__GT_interceptor.call(null,new cljs.core.Keyword(null,"id","id",-1388402092),new cljs.core.Keyword(null,"do-fx","do-fx",1194163050),new cljs.core.Keyword(null,"after","after",594996914),(function re_frame$fx$do_fx_after(context){
if(re_frame.trace.is_trace_enabled_QMARK_.call(null)){
var _STAR_current_trace_STAR_51279 = re_frame.trace._STAR_current_trace_STAR_;
re_frame.trace._STAR_current_trace_STAR_ = re_frame.trace.start_trace.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"op-type","op-type",-1636141668),new cljs.core.Keyword("event","do-fx","event/do-fx",1357330452)], null));

try{try{var seq__51280 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"effects","effects",-282369292).cljs$core$IFn$_invoke$arity$1(context));
var chunk__51281 = null;
var count__51282 = (0);
var i__51283 = (0);
while(true){
if((i__51283 < count__51282)){
var vec__51284 = cljs.core._nth.call(null,chunk__51281,i__51283);
var effect_key = cljs.core.nth.call(null,vec__51284,(0),null);
var effect_value = cljs.core.nth.call(null,vec__51284,(1),null);
var temp__5455__auto___51300 = re_frame.registrar.get_handler.call(null,re_frame.fx.kind,effect_key,false);
if(cljs.core.truth_(temp__5455__auto___51300)){
var effect_fn_51301 = temp__5455__auto___51300;
effect_fn_51301.call(null,effect_value);
} else {
re_frame.loggers.console.call(null,new cljs.core.Keyword(null,"error","error",-978969032),"re-frame: no handler registered for effect:",effect_key,". Ignoring.");
}


var G__51302 = seq__51280;
var G__51303 = chunk__51281;
var G__51304 = count__51282;
var G__51305 = (i__51283 + (1));
seq__51280 = G__51302;
chunk__51281 = G__51303;
count__51282 = G__51304;
i__51283 = G__51305;
continue;
} else {
var temp__5457__auto__ = cljs.core.seq.call(null,seq__51280);
if(temp__5457__auto__){
var seq__51280__$1 = temp__5457__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__51280__$1)){
var c__4319__auto__ = cljs.core.chunk_first.call(null,seq__51280__$1);
var G__51306 = cljs.core.chunk_rest.call(null,seq__51280__$1);
var G__51307 = c__4319__auto__;
var G__51308 = cljs.core.count.call(null,c__4319__auto__);
var G__51309 = (0);
seq__51280 = G__51306;
chunk__51281 = G__51307;
count__51282 = G__51308;
i__51283 = G__51309;
continue;
} else {
var vec__51287 = cljs.core.first.call(null,seq__51280__$1);
var effect_key = cljs.core.nth.call(null,vec__51287,(0),null);
var effect_value = cljs.core.nth.call(null,vec__51287,(1),null);
var temp__5455__auto___51310 = re_frame.registrar.get_handler.call(null,re_frame.fx.kind,effect_key,false);
if(cljs.core.truth_(temp__5455__auto___51310)){
var effect_fn_51311 = temp__5455__auto___51310;
effect_fn_51311.call(null,effect_value);
} else {
re_frame.loggers.console.call(null,new cljs.core.Keyword(null,"error","error",-978969032),"re-frame: no handler registered for effect:",effect_key,". Ignoring.");
}


var G__51312 = cljs.core.next.call(null,seq__51280__$1);
var G__51313 = null;
var G__51314 = (0);
var G__51315 = (0);
seq__51280 = G__51312;
chunk__51281 = G__51313;
count__51282 = G__51314;
i__51283 = G__51315;
continue;
}
} else {
return null;
}
}
break;
}
}finally {if(re_frame.trace.is_trace_enabled_QMARK_.call(null)){
var end__50536__auto___51316 = re_frame.interop.now.call(null);
var duration__50537__auto___51317 = (end__50536__auto___51316 - new cljs.core.Keyword(null,"start","start",-355208981).cljs$core$IFn$_invoke$arity$1(re_frame.trace._STAR_current_trace_STAR_));
cljs.core.swap_BANG_.call(null,re_frame.trace.traces,cljs.core.conj,cljs.core.assoc.call(null,re_frame.trace._STAR_current_trace_STAR_,new cljs.core.Keyword(null,"duration","duration",1444101068),duration__50537__auto___51317,new cljs.core.Keyword(null,"end","end",-268185958),re_frame.interop.now.call(null)));

re_frame.trace.run_tracing_callbacks_BANG_.call(null,end__50536__auto___51316);
} else {
}
}}finally {re_frame.trace._STAR_current_trace_STAR_ = _STAR_current_trace_STAR_51279;
}} else {
var seq__51290 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"effects","effects",-282369292).cljs$core$IFn$_invoke$arity$1(context));
var chunk__51291 = null;
var count__51292 = (0);
var i__51293 = (0);
while(true){
if((i__51293 < count__51292)){
var vec__51294 = cljs.core._nth.call(null,chunk__51291,i__51293);
var effect_key = cljs.core.nth.call(null,vec__51294,(0),null);
var effect_value = cljs.core.nth.call(null,vec__51294,(1),null);
var temp__5455__auto___51318 = re_frame.registrar.get_handler.call(null,re_frame.fx.kind,effect_key,false);
if(cljs.core.truth_(temp__5455__auto___51318)){
var effect_fn_51319 = temp__5455__auto___51318;
effect_fn_51319.call(null,effect_value);
} else {
re_frame.loggers.console.call(null,new cljs.core.Keyword(null,"error","error",-978969032),"re-frame: no handler registered for effect:",effect_key,". Ignoring.");
}


var G__51320 = seq__51290;
var G__51321 = chunk__51291;
var G__51322 = count__51292;
var G__51323 = (i__51293 + (1));
seq__51290 = G__51320;
chunk__51291 = G__51321;
count__51292 = G__51322;
i__51293 = G__51323;
continue;
} else {
var temp__5457__auto__ = cljs.core.seq.call(null,seq__51290);
if(temp__5457__auto__){
var seq__51290__$1 = temp__5457__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__51290__$1)){
var c__4319__auto__ = cljs.core.chunk_first.call(null,seq__51290__$1);
var G__51324 = cljs.core.chunk_rest.call(null,seq__51290__$1);
var G__51325 = c__4319__auto__;
var G__51326 = cljs.core.count.call(null,c__4319__auto__);
var G__51327 = (0);
seq__51290 = G__51324;
chunk__51291 = G__51325;
count__51292 = G__51326;
i__51293 = G__51327;
continue;
} else {
var vec__51297 = cljs.core.first.call(null,seq__51290__$1);
var effect_key = cljs.core.nth.call(null,vec__51297,(0),null);
var effect_value = cljs.core.nth.call(null,vec__51297,(1),null);
var temp__5455__auto___51328 = re_frame.registrar.get_handler.call(null,re_frame.fx.kind,effect_key,false);
if(cljs.core.truth_(temp__5455__auto___51328)){
var effect_fn_51329 = temp__5455__auto___51328;
effect_fn_51329.call(null,effect_value);
} else {
re_frame.loggers.console.call(null,new cljs.core.Keyword(null,"error","error",-978969032),"re-frame: no handler registered for effect:",effect_key,". Ignoring.");
}


var G__51330 = cljs.core.next.call(null,seq__51290__$1);
var G__51331 = null;
var G__51332 = (0);
var G__51333 = (0);
seq__51290 = G__51330;
chunk__51291 = G__51331;
count__51292 = G__51332;
i__51293 = G__51333;
continue;
}
} else {
return null;
}
}
break;
}
}
}));
re_frame.fx.reg_fx.call(null,new cljs.core.Keyword(null,"dispatch-later","dispatch-later",291951390),(function (value){
var seq__51334 = cljs.core.seq.call(null,value);
var chunk__51335 = null;
var count__51336 = (0);
var i__51337 = (0);
while(true){
if((i__51337 < count__51336)){
var map__51338 = cljs.core._nth.call(null,chunk__51335,i__51337);
var map__51338__$1 = ((((!((map__51338 == null)))?(((((map__51338.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__51338.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__51338):map__51338);
var effect = map__51338__$1;
var ms = cljs.core.get.call(null,map__51338__$1,new cljs.core.Keyword(null,"ms","ms",-1152709733));
var dispatch = cljs.core.get.call(null,map__51338__$1,new cljs.core.Keyword(null,"dispatch","dispatch",1319337009));
if(((cljs.core.empty_QMARK_.call(null,dispatch)) || (!(typeof ms === 'number')))){
re_frame.loggers.console.call(null,new cljs.core.Keyword(null,"error","error",-978969032),"re-frame: ignoring bad :dispatch-later value:",effect);
} else {
re_frame.interop.set_timeout_BANG_.call(null,((function (seq__51334,chunk__51335,count__51336,i__51337,map__51338,map__51338__$1,effect,ms,dispatch){
return (function (){
return re_frame.router.dispatch.call(null,dispatch);
});})(seq__51334,chunk__51335,count__51336,i__51337,map__51338,map__51338__$1,effect,ms,dispatch))
,ms);
}


var G__51342 = seq__51334;
var G__51343 = chunk__51335;
var G__51344 = count__51336;
var G__51345 = (i__51337 + (1));
seq__51334 = G__51342;
chunk__51335 = G__51343;
count__51336 = G__51344;
i__51337 = G__51345;
continue;
} else {
var temp__5457__auto__ = cljs.core.seq.call(null,seq__51334);
if(temp__5457__auto__){
var seq__51334__$1 = temp__5457__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__51334__$1)){
var c__4319__auto__ = cljs.core.chunk_first.call(null,seq__51334__$1);
var G__51346 = cljs.core.chunk_rest.call(null,seq__51334__$1);
var G__51347 = c__4319__auto__;
var G__51348 = cljs.core.count.call(null,c__4319__auto__);
var G__51349 = (0);
seq__51334 = G__51346;
chunk__51335 = G__51347;
count__51336 = G__51348;
i__51337 = G__51349;
continue;
} else {
var map__51340 = cljs.core.first.call(null,seq__51334__$1);
var map__51340__$1 = ((((!((map__51340 == null)))?(((((map__51340.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__51340.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__51340):map__51340);
var effect = map__51340__$1;
var ms = cljs.core.get.call(null,map__51340__$1,new cljs.core.Keyword(null,"ms","ms",-1152709733));
var dispatch = cljs.core.get.call(null,map__51340__$1,new cljs.core.Keyword(null,"dispatch","dispatch",1319337009));
if(((cljs.core.empty_QMARK_.call(null,dispatch)) || (!(typeof ms === 'number')))){
re_frame.loggers.console.call(null,new cljs.core.Keyword(null,"error","error",-978969032),"re-frame: ignoring bad :dispatch-later value:",effect);
} else {
re_frame.interop.set_timeout_BANG_.call(null,((function (seq__51334,chunk__51335,count__51336,i__51337,map__51340,map__51340__$1,effect,ms,dispatch,seq__51334__$1,temp__5457__auto__){
return (function (){
return re_frame.router.dispatch.call(null,dispatch);
});})(seq__51334,chunk__51335,count__51336,i__51337,map__51340,map__51340__$1,effect,ms,dispatch,seq__51334__$1,temp__5457__auto__))
,ms);
}


var G__51350 = cljs.core.next.call(null,seq__51334__$1);
var G__51351 = null;
var G__51352 = (0);
var G__51353 = (0);
seq__51334 = G__51350;
chunk__51335 = G__51351;
count__51336 = G__51352;
i__51337 = G__51353;
continue;
}
} else {
return null;
}
}
break;
}
}));
re_frame.fx.reg_fx.call(null,new cljs.core.Keyword(null,"dispatch","dispatch",1319337009),(function (value){
if(!(cljs.core.vector_QMARK_.call(null,value))){
return re_frame.loggers.console.call(null,new cljs.core.Keyword(null,"error","error",-978969032),"re-frame: ignoring bad :dispatch value. Expected a vector, but got:",value);
} else {
return re_frame.router.dispatch.call(null,value);
}
}));
re_frame.fx.reg_fx.call(null,new cljs.core.Keyword(null,"dispatch-n","dispatch-n",-504469236),(function (value){
if(!(cljs.core.sequential_QMARK_.call(null,value))){
return re_frame.loggers.console.call(null,new cljs.core.Keyword(null,"error","error",-978969032),"re-frame: ignoring bad :dispatch-n value. Expected a collection, got got:",value);
} else {
var seq__51354 = cljs.core.seq.call(null,cljs.core.remove.call(null,cljs.core.nil_QMARK_,value));
var chunk__51355 = null;
var count__51356 = (0);
var i__51357 = (0);
while(true){
if((i__51357 < count__51356)){
var event = cljs.core._nth.call(null,chunk__51355,i__51357);
re_frame.router.dispatch.call(null,event);


var G__51358 = seq__51354;
var G__51359 = chunk__51355;
var G__51360 = count__51356;
var G__51361 = (i__51357 + (1));
seq__51354 = G__51358;
chunk__51355 = G__51359;
count__51356 = G__51360;
i__51357 = G__51361;
continue;
} else {
var temp__5457__auto__ = cljs.core.seq.call(null,seq__51354);
if(temp__5457__auto__){
var seq__51354__$1 = temp__5457__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__51354__$1)){
var c__4319__auto__ = cljs.core.chunk_first.call(null,seq__51354__$1);
var G__51362 = cljs.core.chunk_rest.call(null,seq__51354__$1);
var G__51363 = c__4319__auto__;
var G__51364 = cljs.core.count.call(null,c__4319__auto__);
var G__51365 = (0);
seq__51354 = G__51362;
chunk__51355 = G__51363;
count__51356 = G__51364;
i__51357 = G__51365;
continue;
} else {
var event = cljs.core.first.call(null,seq__51354__$1);
re_frame.router.dispatch.call(null,event);


var G__51366 = cljs.core.next.call(null,seq__51354__$1);
var G__51367 = null;
var G__51368 = (0);
var G__51369 = (0);
seq__51354 = G__51366;
chunk__51355 = G__51367;
count__51356 = G__51368;
i__51357 = G__51369;
continue;
}
} else {
return null;
}
}
break;
}
}
}));
re_frame.fx.reg_fx.call(null,new cljs.core.Keyword(null,"deregister-event-handler","deregister-event-handler",-1096518994),(function (value){
var clear_event = cljs.core.partial.call(null,re_frame.registrar.clear_handlers,re_frame.events.kind);
if(cljs.core.sequential_QMARK_.call(null,value)){
var seq__51370 = cljs.core.seq.call(null,value);
var chunk__51371 = null;
var count__51372 = (0);
var i__51373 = (0);
while(true){
if((i__51373 < count__51372)){
var event = cljs.core._nth.call(null,chunk__51371,i__51373);
clear_event.call(null,event);


var G__51374 = seq__51370;
var G__51375 = chunk__51371;
var G__51376 = count__51372;
var G__51377 = (i__51373 + (1));
seq__51370 = G__51374;
chunk__51371 = G__51375;
count__51372 = G__51376;
i__51373 = G__51377;
continue;
} else {
var temp__5457__auto__ = cljs.core.seq.call(null,seq__51370);
if(temp__5457__auto__){
var seq__51370__$1 = temp__5457__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__51370__$1)){
var c__4319__auto__ = cljs.core.chunk_first.call(null,seq__51370__$1);
var G__51378 = cljs.core.chunk_rest.call(null,seq__51370__$1);
var G__51379 = c__4319__auto__;
var G__51380 = cljs.core.count.call(null,c__4319__auto__);
var G__51381 = (0);
seq__51370 = G__51378;
chunk__51371 = G__51379;
count__51372 = G__51380;
i__51373 = G__51381;
continue;
} else {
var event = cljs.core.first.call(null,seq__51370__$1);
clear_event.call(null,event);


var G__51382 = cljs.core.next.call(null,seq__51370__$1);
var G__51383 = null;
var G__51384 = (0);
var G__51385 = (0);
seq__51370 = G__51382;
chunk__51371 = G__51383;
count__51372 = G__51384;
i__51373 = G__51385;
continue;
}
} else {
return null;
}
}
break;
}
} else {
return clear_event.call(null,value);
}
}));
re_frame.fx.reg_fx.call(null,new cljs.core.Keyword(null,"db","db",993250759),(function (value){
if(!((cljs.core.deref.call(null,re_frame.db.app_db) === value))){
return cljs.core.reset_BANG_.call(null,re_frame.db.app_db,value);
} else {
return null;
}
}));

//# sourceMappingURL=fx.js.map
