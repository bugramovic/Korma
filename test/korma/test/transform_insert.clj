(ns korma.test.transform-insert
    (:use [korma.db])
    (:use [korma.core]) )

(defdb h2 {:classname "org.h2.Driver"
           :subprotocol "h2"
           :subname "mem:"
           :user "sa"
           :password ""})

(exec-raw "drop table testcase")

(exec-raw "create table testcase (
  id      identity PRIMARY KEY,
  counter integer)")

(defentity no-transform
  (table :testcase))

(defn myinc [x] (if (nil? x) nil (inc x) ) )

(defentity with-transform
  (table :testcase)
  (transform (fn [e] (if (nil? (:COUNTER e)) e (update-in e [:COUNTER] inc  ) ) ) )
  )

;works
(insert no-transform (values {:COUNTER 0}))
;throws NPE
(insert with-transform (values {:COUNTER 0}))

(select no-transform)
(select with-transform)
(use 'clojure.stacktrace)
(print-stack-trace *e 20)