(ns names.core
  (:refer-clojure :exclude [next])
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def nouns
  (-> (io/resource "nouns.txt")
      (slurp)
      (str/split-lines)))

(def adjectives
  (-> (io/resource "adjectives.txt")
      (slurp)
      (str/split-lines)))

(defn- next* [{:keys [numbered?] :or {numbered? false}} gen]
  (let [noun-idx (.nextInt gen (dec (count nouns)))
        adj-idx (.nextInt gen (dec (count adjectives)))]
    (if numbered?
      (format "%s-%s-%04d" (get adjectives adj-idx) (get nouns noun-idx) (mod (.nextInt gen 10000) 10000))
      (format "%s-%s" (get adjectives adj-idx) (get nouns noun-idx)))))

(defprotocol IGenerator
  (next [this] "Returns the next name in the random sequence.")
  (next-n [this n] "Returns the next n names in the random sequence."))

(deftype Generator [config gen]
  IGenerator
  (next [_this]
    (next* config gen))
  (next-n [_this n]
    (repeatedly n #(next* config gen))))

(defn create
  ([] (create {}))
  ([{:keys [seed] :as config}]
   (->Generator config (if (some? seed)
                         (java.util.Random. seed)
                         (java.util.Random.)))))
