(ns names.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [names.core :as sut]))

(def adj+noun-pattern
  #"[a-z]+-[a-z]+")

(def numbered-adj+noun-pattern
  #"[a-z]+-[a-z]+[0-9]{4}")

(deftest unseeded-generator-test
  (testing "Can generate random words"
    (let [gen (sut/create)
          next-word (sut/next gen)]
      (is (string? next-word))
      (is (re-seq adj+noun-pattern next-word))
      (testing "Can generate many words"
        (let [next-n (sut/next-n gen 15)]
          (is (= 15 (count next-n)))
          (is (every? #(and (string? %) (re-seq adj+noun-pattern %)) next-n)))))))
