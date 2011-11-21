(ns korma.test.join-test
    (:use [korma.db])
    (:use [korma.core])
    (:use [clojure.test])
  )

(defentity test1)

(deftest sp-join
  (sql-only
    (select test1
       (join :table :test2 :ignore :ignore)
      )
  )
)

(run-tests)
