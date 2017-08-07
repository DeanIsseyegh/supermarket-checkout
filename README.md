# supermarket-checkout
Basic app to practice TDD/Java/Domain Design

# Development Approach
Spent 30ish minutes doing some Domain Modelling and thinking about the problem and different classes that could be needed.

Done using TDD (so did not bother creating a CLI).

Took a mockist approach where possible to make tests as independent as possible.

# Design Decisions
Used the Open-Closed principle when modelling the DiscountRule object. This is so new ones can easily be added without modifying any existing objects.

Discount Factory was created in order to encapsulate the creation of the Discount Rules logic so when a new Discount is created the only place in the code you would have to change is the logic inside the Discount Factory.

Discount Rules are given a "priorty" so you could define the order they run it, as that can be very important.

Items also follow the Open-Closed principle and are easily extendible. They contain the methods `markAsDiscounted()` and  `hasDiscountApplied()` as usually when an item is involved in a discount, they cannot be involved in other discounts.

Rules I actually implemented marked items involved in a discount so they couldn't be involved in two. However, because the method 
`DiscountRule.filterDiscountableItems` is overrideable, any new rule can choose whether to mark the items involved in the discount or not (some may not want to).

Currency is configurable so application could work in other countries.

# Things I would have done differently (given more time)
1. Make the Discount Factory produce discounts based on a settings file (XML) where you could define the discount rule name and its priority. This would allow you to easily add/remove Discount Rules without modifying any code or unit tests.

2. Items would have their name set as an `Enum` with a `String` value, instead of just Strings as its currently implemented. This is so you could would be able to update their String name representation which shows on the Receipt, without affecting other bits of logic in some Discount Rules which do String comparison checks.

3. Discount Factory will have outputted an order set such as a TreeHashSet which provides the benefits of A) Ensuring no duplicate sets and B) Use natural ordering of a Discount Rule (which could be done by implementing the `Comparable` interface to the `DiscountRule` object.

4. Implemented some of the other rules mentioned in the document.

5. Made Currency configurable via a settings file too.

6. Added more integration tests. The one integration test I did is not enough to cover many happy path cases, nor any edge cases.
