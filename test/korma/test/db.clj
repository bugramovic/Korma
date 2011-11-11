(ns korma.test.db
  (:use [korma.db])
  (:use [korma.core]) )


;(defdb h2 {:classname "org.h2.Driver"
;           :subprotocol "h2"
;           :subname "mem:"
;           :user "sa"
;           :password ""})

(defdb playground {:classname "org.postgresql.Driver"
                  :subprotocol "postgresql"
                  :subname "playground"
                  :user "postgres"
                  :password "theend42"})

;(exec-raw "create table testcase(
;            name varchar(2000),
;            test_date date )" )

(defentity testcase)

(defmethod convert-before-update java.util.Date [util-date]
  (new java.sql.Date (. util-date getTime)) )

;insert wrong type, to trigger an exception
;values -> [ {} ]
(update testcase
  (set-fields {:name "updated4"})
  (where {:name "updated3"}))

(insert testcase (values {:name "test" :test_date (new java.util.Date) }))


;(insert testcase (values {:name "test" :test_date (new java.sql.Date 100 10 10) }))

(select testcase)