# Cart with event sourcing

Trying to implement a shopping cart with event sourcing techniques.

## Components

### Done

* Event store (memory based done, jpa in progress)

### TODO

* Controller
* CommandService (takes commands, performs business logic and then fires events?!)
* QueryService (creates domain objects by replaying the event log)
* Materializing


### Controller
  
* `GET /cart/{cartId}`
* `POST /cart`
* `POST /cart/{cartId}/{item}`
* `DELETE /cart/{cartId}/{sku}`

### CommandService 

* createNewCart()
* addToCart(cart, item)
* removeFromCart(cart, item)
* payCart(cart, payment)
   
### QueryService 

* getCart(cartId)

### Materializing

Just saving the domain object as json somewhere?!

## Events

* CartCreated
* CartItemAdded
* CartItemRemoved
* PaymentAdded

## Database model

The domain events will be stored in one table with the following attributes:

* id
* timestamp
* version
* payload

The event itself will be stored in serialized form in the payload field.
This decouples the database structure with the events themselves.

## Links

* [Event sourcing in practice](https://ookami86.github.io/event-sourcing-in-practice)
* [Some slides from InnoQ](https://de.slideshare.net/mploed/event-sourcing-einfuhrung-und-best-practices)
* [Microsoft Azure article](https://docs.microsoft.com/en-us/azure/architecture/patterns/event-sourcing)
* [Thoughts on event stores](https://cqrs.wordpress.com/documents/building-event-storage/)
* [Domain Command Patterns - Validation](https://jimmybogard.com/domain-command-patterns-validation/)
* [Domain Command Patterns - Handlers](https://jimmybogard.com/domain-command-patterns-handlers/)


