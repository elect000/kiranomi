// Compiled by ClojureScript 1.10.238 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
goog.require('cljs.spec.alpha');
cljs.repl.print_doc = (function cljs$repl$print_doc(p__49962){
var map__49963 = p__49962;
var map__49963__$1 = ((((!((map__49963 == null)))?(((((map__49963.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__49963.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__49963):map__49963);
var m = map__49963__$1;
var n = cljs.core.get.call(null,map__49963__$1,new cljs.core.Keyword(null,"ns","ns",441598760));
var nm = cljs.core.get.call(null,map__49963__$1,new cljs.core.Keyword(null,"name","name",1843675177));
cljs.core.println.call(null,"-------------------------");

cljs.core.println.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1((function (){var temp__5457__auto__ = new cljs.core.Keyword(null,"ns","ns",441598760).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(temp__5457__auto__)){
var ns = temp__5457__auto__;
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(ns),"/"].join('');
} else {
return null;
}
})()),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Protocol");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m))){
var seq__49965_49987 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__49966_49988 = null;
var count__49967_49989 = (0);
var i__49968_49990 = (0);
while(true){
if((i__49968_49990 < count__49967_49989)){
var f_49991 = cljs.core._nth.call(null,chunk__49966_49988,i__49968_49990);
cljs.core.println.call(null,"  ",f_49991);


var G__49992 = seq__49965_49987;
var G__49993 = chunk__49966_49988;
var G__49994 = count__49967_49989;
var G__49995 = (i__49968_49990 + (1));
seq__49965_49987 = G__49992;
chunk__49966_49988 = G__49993;
count__49967_49989 = G__49994;
i__49968_49990 = G__49995;
continue;
} else {
var temp__5457__auto___49996 = cljs.core.seq.call(null,seq__49965_49987);
if(temp__5457__auto___49996){
var seq__49965_49997__$1 = temp__5457__auto___49996;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__49965_49997__$1)){
var c__4319__auto___49998 = cljs.core.chunk_first.call(null,seq__49965_49997__$1);
var G__49999 = cljs.core.chunk_rest.call(null,seq__49965_49997__$1);
var G__50000 = c__4319__auto___49998;
var G__50001 = cljs.core.count.call(null,c__4319__auto___49998);
var G__50002 = (0);
seq__49965_49987 = G__49999;
chunk__49966_49988 = G__50000;
count__49967_49989 = G__50001;
i__49968_49990 = G__50002;
continue;
} else {
var f_50003 = cljs.core.first.call(null,seq__49965_49997__$1);
cljs.core.println.call(null,"  ",f_50003);


var G__50004 = cljs.core.next.call(null,seq__49965_49997__$1);
var G__50005 = null;
var G__50006 = (0);
var G__50007 = (0);
seq__49965_49987 = G__50004;
chunk__49966_49988 = G__50005;
count__49967_49989 = G__50006;
i__49968_49990 = G__50007;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_50008 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__3922__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__3922__auto__)){
return or__3922__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_50008);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_50008)))?cljs.core.second.call(null,arglists_50008):arglists_50008));
}
} else {
}
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"special-form","special-form",-1326536374).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Special Form");

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.contains_QMARK_.call(null,m,new cljs.core.Keyword(null,"url","url",276297046))){
if(cljs.core.truth_(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))){
return cljs.core.println.call(null,["\n  Please see http://clojure.org/",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))].join(''));
} else {
return null;
}
} else {
return cljs.core.println.call(null,["\n  Please see http://clojure.org/special_forms#",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Macro");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"REPL Special Function");
} else {
}

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
var seq__49969_50009 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__49970_50010 = null;
var count__49971_50011 = (0);
var i__49972_50012 = (0);
while(true){
if((i__49972_50012 < count__49971_50011)){
var vec__49973_50013 = cljs.core._nth.call(null,chunk__49970_50010,i__49972_50012);
var name_50014 = cljs.core.nth.call(null,vec__49973_50013,(0),null);
var map__49976_50015 = cljs.core.nth.call(null,vec__49973_50013,(1),null);
var map__49976_50016__$1 = ((((!((map__49976_50015 == null)))?(((((map__49976_50015.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__49976_50015.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__49976_50015):map__49976_50015);
var doc_50017 = cljs.core.get.call(null,map__49976_50016__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_50018 = cljs.core.get.call(null,map__49976_50016__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_50014);

cljs.core.println.call(null," ",arglists_50018);

if(cljs.core.truth_(doc_50017)){
cljs.core.println.call(null," ",doc_50017);
} else {
}


var G__50019 = seq__49969_50009;
var G__50020 = chunk__49970_50010;
var G__50021 = count__49971_50011;
var G__50022 = (i__49972_50012 + (1));
seq__49969_50009 = G__50019;
chunk__49970_50010 = G__50020;
count__49971_50011 = G__50021;
i__49972_50012 = G__50022;
continue;
} else {
var temp__5457__auto___50023 = cljs.core.seq.call(null,seq__49969_50009);
if(temp__5457__auto___50023){
var seq__49969_50024__$1 = temp__5457__auto___50023;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__49969_50024__$1)){
var c__4319__auto___50025 = cljs.core.chunk_first.call(null,seq__49969_50024__$1);
var G__50026 = cljs.core.chunk_rest.call(null,seq__49969_50024__$1);
var G__50027 = c__4319__auto___50025;
var G__50028 = cljs.core.count.call(null,c__4319__auto___50025);
var G__50029 = (0);
seq__49969_50009 = G__50026;
chunk__49970_50010 = G__50027;
count__49971_50011 = G__50028;
i__49972_50012 = G__50029;
continue;
} else {
var vec__49978_50030 = cljs.core.first.call(null,seq__49969_50024__$1);
var name_50031 = cljs.core.nth.call(null,vec__49978_50030,(0),null);
var map__49981_50032 = cljs.core.nth.call(null,vec__49978_50030,(1),null);
var map__49981_50033__$1 = ((((!((map__49981_50032 == null)))?(((((map__49981_50032.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__49981_50032.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__49981_50032):map__49981_50032);
var doc_50034 = cljs.core.get.call(null,map__49981_50033__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_50035 = cljs.core.get.call(null,map__49981_50033__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_50031);

cljs.core.println.call(null," ",arglists_50035);

if(cljs.core.truth_(doc_50034)){
cljs.core.println.call(null," ",doc_50034);
} else {
}


var G__50036 = cljs.core.next.call(null,seq__49969_50024__$1);
var G__50037 = null;
var G__50038 = (0);
var G__50039 = (0);
seq__49969_50009 = G__50036;
chunk__49970_50010 = G__50037;
count__49971_50011 = G__50038;
i__49972_50012 = G__50039;
continue;
}
} else {
}
}
break;
}
} else {
}

if(cljs.core.truth_(n)){
var temp__5457__auto__ = cljs.spec.alpha.get_spec.call(null,cljs.core.symbol.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.ns_name.call(null,n))].join(''),cljs.core.name.call(null,nm)));
if(cljs.core.truth_(temp__5457__auto__)){
var fnspec = temp__5457__auto__;
cljs.core.print.call(null,"Spec");

var seq__49983 = cljs.core.seq.call(null,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"args","args",1315556576),new cljs.core.Keyword(null,"ret","ret",-468222814),new cljs.core.Keyword(null,"fn","fn",-1175266204)], null));
var chunk__49984 = null;
var count__49985 = (0);
var i__49986 = (0);
while(true){
if((i__49986 < count__49985)){
var role = cljs.core._nth.call(null,chunk__49984,i__49986);
var temp__5457__auto___50040__$1 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__5457__auto___50040__$1)){
var spec_50041 = temp__5457__auto___50040__$1;
cljs.core.print.call(null,["\n ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.name.call(null,role)),":"].join(''),cljs.spec.alpha.describe.call(null,spec_50041));
} else {
}


var G__50042 = seq__49983;
var G__50043 = chunk__49984;
var G__50044 = count__49985;
var G__50045 = (i__49986 + (1));
seq__49983 = G__50042;
chunk__49984 = G__50043;
count__49985 = G__50044;
i__49986 = G__50045;
continue;
} else {
var temp__5457__auto____$1 = cljs.core.seq.call(null,seq__49983);
if(temp__5457__auto____$1){
var seq__49983__$1 = temp__5457__auto____$1;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__49983__$1)){
var c__4319__auto__ = cljs.core.chunk_first.call(null,seq__49983__$1);
var G__50046 = cljs.core.chunk_rest.call(null,seq__49983__$1);
var G__50047 = c__4319__auto__;
var G__50048 = cljs.core.count.call(null,c__4319__auto__);
var G__50049 = (0);
seq__49983 = G__50046;
chunk__49984 = G__50047;
count__49985 = G__50048;
i__49986 = G__50049;
continue;
} else {
var role = cljs.core.first.call(null,seq__49983__$1);
var temp__5457__auto___50050__$2 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__5457__auto___50050__$2)){
var spec_50051 = temp__5457__auto___50050__$2;
cljs.core.print.call(null,["\n ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.name.call(null,role)),":"].join(''),cljs.spec.alpha.describe.call(null,spec_50051));
} else {
}


var G__50052 = cljs.core.next.call(null,seq__49983__$1);
var G__50053 = null;
var G__50054 = (0);
var G__50055 = (0);
seq__49983 = G__50052;
chunk__49984 = G__50053;
count__49985 = G__50054;
i__49986 = G__50055;
continue;
}
} else {
return null;
}
}
break;
}
} else {
return null;
}
} else {
return null;
}
}
});

//# sourceMappingURL=repl.js.map
