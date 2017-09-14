# Cart with event sourcing

Trying to implement a shopping cart with event sourcing techniques.

## Todo next

* Implement DAO with jdbctemplate or sth. We don't seem to profit much from
  hibernate as the storing of the event information will require multiple
  (transaction)

## Links

* [Some slides from InnoQ](https://de.slideshare.net/mploed/event-sourcing-einfuhrung-und-best-practices)
* [Microsoft Azure article](https://docs.microsoft.com/en-us/azure/architecture/patterns/event-sourcing)
* [Thoughts on event stores](https://cqrs.wordpress.com/documents/building-event-storage/)

## Modeling Cart with Domain events

* CartCreatedEvent
* CartItemAddedEvent
* CartItemRemovedEvent
* CartPayedEvent

## Database model

The domain events will be stored in one table with the following attributes:

* id
* date
* payload

The event itself will be stored in serialized form in the payload field.
This decouples the database structure with the events itself.


## Other classes

* CartController
  * GET /cart/{cartId}
  * POST /cart
  * POST /cart/{cartId}/{item}

* CartQueryService
  * findById(cartId)

* CartQueryDao

* CartCommandService
  * createNewCart()
  * addToCart(cart, item)
  * removeFromCart(cart, item)
  * payCart(cart, payment)

* CartCommandDao


