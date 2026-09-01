# names

A small Clojure library for generating random, human-friendly names such as
`delicate-firefly` or `aquatic-volcano-0428`. Inspired by the Rust
[`names`](https://crates.io/crates/names) crate.

[![Clojars Project](https://img.shields.io/clojars/v/net.clojars.idiomattic/names.svg)](https://clojars.org/net.clojars.idiomattic/names)

## Installation

deps.edn:

```clojure
net.clojars.idiomattic/names {:mvn/version "0.9.x"}
```

Leiningen/Boot:

```clojure
[net.clojars.idiomattic/names "0.9.x"]
```

## Usage

```clojure
(require '[names.core :as names])

;; Create a generator and pull names from it.
(def gen (names/create))

(names/next gen)
;; => "delicate-firefly"

(names/next-n gen 3)
;; => ("young-lawyer" "exotic-cream" "late-owl")
```

### Options

`create` accepts a config map:

| Key           | Default            | Description                                               |
| ------------- | ------------------ | --------------------------------------------------------- |
| `:seed`       | random             | A `long` seed for reproducible sequences.                 |
| `:numbered?`  | `false`            | Append a random 4-digit suffix, e.g. `busy-oranges-3474`. |
| `:adjectives` | built-in word list | Custom vector of adjectives to draw from.                 |
| `:nouns`      | built-in word list | Custom vector of nouns to draw from.                      |

```clojure
;; Reproducible output with a seed.
(names/next (names/create {:seed 10}))
;; => "repulsive-learning"

;; Numbered names.
(names/next (names/create {:numbered? true}))
;; => "busy-oranges-3474"

;; Custom vocabulary.
(names/next (names/create {:adjectives ["imaginary"] :nouns ["roll"]}))
;; => "imaginary-roll"
```

## Development

Run the tests:

    clojure -T:build test

Build the JAR and run the CI pipeline (tests + JAR):

    clojure -T:build ci

Install locally (run `ci` first):

    clojure -T:build install

Deploy to Clojars (run `ci` first; needs `CLOJARS_USERNAME` and
`CLOJARS_PASSWORD` environment variables):

    clojure -T:build deploy

## License

Copyright © 2026 Matthew Lese

Distributed under the MIT License. See [LICENSE](LICENSE).
