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

(reg-event-db
 :set-title
 (fn [db [_ title]]
   (assoc db :title title)))

(reg-event-db
 :set-login
 (fn [db [_]]
   (assoc db :login? true)))

(reg-event-db
 :set-name
 (fn [db [_ name]]
   (assoc db :name name)))
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

(reg-sub
 :title
 (fn [db _]
   (-> db :title)))

(reg-sub
 :login?
 (fn [db _]
   (-> db :login?)))

(reg-sub
 :name
 (fn [db _]
   (-> db :name)))


;; From reagent-form
(reg-event-db
 :init
 (fn [_ _]
   {:doc {}}))

(reg-sub
 :doc
 (fn [db _]
   (:doc db)))

(reg-sub
 :value
 :<- [:doc]
 (fn [doc [_ path]]
   (get-in doc path)))

(reg-event-db
 :set-value
 (fn [db [_ path value]]
   (assoc-in db (into [:doc] path) value)))

(reg-event-db
 :set-doc
 (fn [db [_ value]]
   (assoc db :doc value)))
