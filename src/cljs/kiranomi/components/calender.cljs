(ns kiranomi.components.calender
  (:require [cljsjs.moment :as moment]
            [re-frame.core :as rf]))

(defn get-today []
  (-> (js/moment)
      (.date)))

(defn get-date [mp]
  (let [today (-> (js/moment)
                  (.add mp "month"))
        month (-> today
                  (.month))
        start-week (-> today
                       (.startOf "month")
                       (.days))
        end-day (-> today
                    (.endOf "month")
                    (.date))
        end-next-day (-> today
                         (.startOf "month")
                         (.add -1 "day")
                         (.date))]
    {:start-week start-week
     :end-day end-day
     :month month
     :end-next-day end-next-day}))

(defn get-cal-array [mp]
  (let [m (get-date mp)
        start-week (:start-week m)
        month (:month m)
        end-day (:end-day m)
        end-next-day (:end-next-day m)
        array-start (inc (- end-next-day start-week))
        ar (concat (map (fn [a] (+ (* 100 month) a))
                        (range array-start (inc end-next-day)))
                   (map (fn [a] (+ (* 100 (inc month)) a)) (range 1 (inc end-day))))
        s (- 7 (mod (count ar) 7))
        l (map (fn [a] (+ (* 100 (+ 2 month)) a)) (range 1 (inc s)))
        arr (concat ar l)
        r (range (inc s) (+ 8 s))
        arrays (if (== 5 (/ (count arr) 7))
                 (concat arr (map (fn [a] (+ (* 100 (+ 2 month)) a)) r))
                 arr)]
    {:month (inc month) :calender (partition 7 arrays)}))

(defn gen-key []
  (gensym "key-"))

(def en-weeks (list "Sunday" "Monday" "Tuesday" "Wednesday" "Thursday" "Friday" "Saturday"))

(def en-weeks-s (list "Sun." "Mon." "Tue." "Wed." "Thu." "Fri." "Sat."))

(def months (list "January" "February" "March" "April" "May" "June"
                  "July" "August" "September" "October" "November" "December"))


(defn cal-array->hiccup [{:keys [month calender]}]
  [:div.container
   [:div.row.justify-content-center.pt-5
    [:div.text-center [:h1 (nth months (mod (dec month) 12))]]]
   [:div.row.mt-3
    [:div.col-1.my-auto
     [:i.fa.fa-chevron-left.fa-2x {:aria-hidden "false"
                                   :on-click #(rf/dispatch [:month-adder -1])}]]
    [:div.col-10
     [:div.content
      [:div.justify-content-center
       [:div.d-flex.flex-fill
        [:div.flex-column.flex-fill
         [:div.d-flex {:key (gen-key)}
          (doall
           (map (fn [i] [:div.flex-fill
                         {:key (gen-key)
                          :style {:border-bottom "2px solid silver"
                                  :font-size "1.2em"}}
                         i])
                en-weeks-s))]
         (doall
          (map (fn [l]
                 [:div.d-flex {:key (gen-key)
                               :style {:border-left "2px solid silver"}}
                  (doall
                   (map (fn [i] [:div.flex-fill.pb-5
                                 {:class
                                  (list
                                   :op-1
                                   (if (some #(= % i)
                                             @(rf/subscribe [:dates]))
                                     :bg-green
                                     (if (= i (+ (* (inc (-> (js/moment) (.month))) 100)
                                                 (-> (js/moment) (.date))))
                                       :bg-blue
                                       :bg-white)))
                                  :key (gen-key)
                                  :style  {:border-right "2px solid silver"
                                           :border-bottom "2px solid silver"
                                           :display "block"
                                           :position "relative"}
                                  :on-click #(rf/dispatch [:push-date i])}
                                 [:span.pull-right
                                  {:style {:font-size "1em"
                                           :opacity "0.5"}}
                                  (if (> 1.0 (/ (mod i 100) 10))
                                    [:span {:style {:padding-left "1em"}} (mod i 100)]
                                    [:span {:style {:margin-left "0.5em"}} (mod i 100)])]] ) l))])
               calender))]]]]]
    [:div.col-1.my-auto
     [:i.fa.fa-chevron-right.fa-2x {:aria-hidden "false"
                                    :on-click #(rf/dispatch [:month-adder 1])}]]]])
