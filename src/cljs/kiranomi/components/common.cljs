(ns kiranomi.components.common
  (:require [re-frame.core :as rf]
            [reagent-forms.core :refer [bind-fields]]))

(defn input2 [type id placeholder fields]
  [:input.form-control-lg.col-12
   {:type type
    :placeholder placeholder
    :value (id @fields)
    :on-change #(swap! fields assoc id (-> % .-target .-value))}])

(defn input [type id placeholder fields]
  [:input.form-control-lg.col-11
   {:type type
    :placeholder placeholder
    :value (id @fields)
    :on-change #(swap! fields assoc id (-> % .-target .-value))}])

(defn form-input [type label id placeholder fields optional?]
  [:div.form-group
   [:label {:style {:font-size "1.5em"}} label
    ]
   (if optional?
     [input type id placeholder fields]
     [:div.input-group.d-flex
      [input type id placeholder fields]
      [:div.col-1
       [:span.input-group-addon {:style {:font-size "1.5em"}} "*"]]])])

(defn text-input [label id placeholder fields & [optional?]]
  (form-input :text label id placeholder fields optional?))

(defn password-input [label id placeholder fields & [optional?]]
  (form-input :password label id placeholder fields optional?))

(defn modal [header body footer]
  [:div
   [:div.modal-dialog.modal-lg
    [:div.modal-content
     [:div.modal-header [:h2 header]]
     [:div.modal-body body]
     [:div.modal-footer
      [:div.bootstrap-dialog-footer {:style {:width "100%"}}
       footer]]]]
   [:div.modal-backdrop.fage.in]])

(def events
  {:get (fn [path] @(rf/subscribe [:value path]))
   :save! (fn [path value] (rf/dispatch [:set-value path value]))
   :update! (fn [path save-fn value]
              (rf/dispatch [:set-value path
                            (save-fn @(rf/subscribe [:value path]) value)]))
   :doc (fn [] @(rf/subscribe [:doc]))})
