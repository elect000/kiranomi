(ns kiranomi.events
  (:require [re-frame.core :refer [dispatch reg-event-db reg-sub]]))

;;dispatchers

(reg-event-db
  :navigate
  (fn [db [_ route]]
    (assoc db :route route)))

(reg-event-db
  :set-docs
  (fn [db [_ docs]]
    (assoc db :docs docs)))

(reg-event-db
 :set-modal
 (fn [db [_ modal]]
   (assoc db :modal modal)))

(reg-event-db
 :remove-modal
 (fn [db [_]]
   (dissoc db :modal)))

(reg-event-db
 :push-date
 (fn [db [_ date]]
   (if (some #(= % date) (:dates db))
     (assoc db :dates (remove #(= % date) (:dates db)))
     (assoc db :dates (cons date (:dates db))))))

(reg-event-db
 :month-adder
 (fn [db [_ adder]]
   (assoc db :month-add (+ adder (if-let [a (:month-add db)] a 0)))))
;;subscriptions

(reg-sub
  :route
  (fn [db _]
    (-> db :route)))

(reg-sub
  :page
  :<- [:route]
  (fn [route _]
    (-> route :data :name)))

(reg-sub
  :docs
  (fn [db _]
    (:docs db)))

(reg-sub
 :modal
 (fn [db _]
   (-> db :modal)))

(reg-sub
 :dates
 (fn [db _]
   (-> db :dates)))

(reg-sub
 :month-add
 (fn [db _]
   (if-let [a (-> db :month-add)]
     a 0)))
