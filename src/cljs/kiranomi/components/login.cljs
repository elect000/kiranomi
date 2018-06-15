(ns kiranomi.components.login
  (:require [ajax.core :as ajax]
            [re-frame.core :as rf]
            [reagent.core :refer [atom]]
            [kiranomi.components.common :as c]))

(defn login! [fields error]
  #(rf/dispatch [:calender]))

(defn login-form []
  (let [fields (atom {})
        error (atom nil)]
    (fn []
      [c/modal
       [:div "Login"]
       [:div
        [:div
         [:div.well.well-sm
          [c/text-input "name" :id "enter a user name" fields]
          [c/password-input "password" :id "enter your password" fields]
          (when-let [error @error]
            [:div.alert.alert-danger error])]
         [:div.container
          [:div.row
           [:dov.col-4.text-center
            [:button.btn.btn-danger.btn-lg
             {:on-click #(rf/dispatch [:remove-modal])}
             "Exit"]]
           [:div.col-4.offset-4.text-center
            [:button.btn.btn-primary.btn-lg
             {:on-click #(login! fields error)}
             "Login"]]
           ]]]]])))

(defn login-button []
  [:button.btn-primary.btn.btn-lg
   {:on-click #(rf/dispatch [:set-modal login-form])} "Login"])

