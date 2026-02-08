# Virtual World (Wirtualny Świat)

A simple, console-based “virtual world” simulation written in Java implementing Object-Oriented Design principles.  
The program runs a turn-based ecosystem on a square grid, where animals move, plants spread, organisms fight, and the human player can move manually or activate special abilities.

## Project overview

The world consists of organisms placed on a board:
- **Animals** move each turn and may collide with other organisms.
- **Plants** do not move but can attempt to spread into nearby free tiles.
- **Collisions** resolve combat, reproduction, or special interactions depending on species.
- The simulation continues until the **human dies**.

The code is organized into packages:
- `struktury/` – core world structures (world, organism base classes, positions, movement vectors, etc.)
- `zwierzeta/` – animals (including the human)
- `rosliny/` – plants

## How to run

Run the main class: `struktury.Swiat`

The game is controlled from the console (standard input).

## Controls

### Movement (human)
Use **WASD**-style input:
- `w` – up
- `s` – down
- `a` – left
- `d` – right
- diagonals:
  - `aw` – up-left
  - `dw` – up-right
  - `as` – down-left
  - `ds` – down-right

### Special abilities (human)
Instead of moving, you can activate a special ability by entering a digit:
- `1` – **Immortality** (Nieśmiertelność)
- `2` – **Magic Elixir** (Magiczny Eliksir)
- `3` – **Antelope Speed** (Szybkość antylopy)
- `4` – **Alzur’s Shield** (Tarcza Alzura)
- `5` – **Burning Aura** (Całopalenie)

## Simulation rules / assumptions

- **Combat resolution:**  
  If the attacked organism has **equal or higher strength** to the attacker, **the attacker wins**.
- **Symbols on the map:**  
  Organisms are displayed using a single character:
  - first letter of the name (if duplicates exist, another letter is used)
  - **lowercase** = plant
  - **uppercase** = animal
  - the **human** is always shown as `X`
- **Diagonal movement** counts as **one step**.
- **Movement range (N):**  
  Range `N` means an animal can move to any tile reachable in **up to N steps** (it can also move fewer steps than the maximum).
- **Plant spreading chance:**  
  A plant’s attempt to spread during its action succeeds with probability **1/10**.

## Example special interactions / behaviors

This project includes multiple animals and plants, each with its own behavior, which are, for example:
- animals with special movement/defense behavior (e.g., faster movement, escaping, blocking weak attacks)
- plants that boost strength, spread more aggressively, or are dangerous on contact
- a special “cyber-sheep” type that interacts with a specific dangerous plant uniquely