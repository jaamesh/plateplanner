import shoppingListService from "../services/shoppingListService";
import { useEffect, useState } from "react";

function ShoppingListUI() {
  const [shoppingList, setShoppingList] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  const hasItmes = shoppingList?.shoppingListItems?.length > 0;

  useEffect(() => {
    setLoading(true);
    setError(null);

    shoppingListService
      .createOrUpdateShoppingList()
      .then((response) => {
        setShoppingList(response.data);
      })
      .catch((err) => {
        setError(err.messag || "Failed to load shopping list.");
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  if (error) {
    return <p className="error">Error: {error}</p>;
  }

  if (loading) {
    return <p>Loading shopping list...</p>;
  }

  return (
    <div className="card">
      {shoppingList && hasItmes ? (
        <ul className="list-group">
          {shoppingList.shoppingListItems.map((item, index) => (
            <li key={index} className="list-group-item">
              {item.ingredient.name} - {item.quantity} {item.unit}
            </li>
          ))}
        </ul>
      ) : (
        <p>No items yet.</p>
      )}
    </div>
  );
}

export default ShoppingListUI;
