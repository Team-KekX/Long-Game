import React, { useState } from 'react';
import { TextField, IconButton, List, ListItem, ListItemIcon, ListItemText, Checkbox, Typography, ListItemButton } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import './ShoppingList.css';

interface ShoppingItem {
  id: string;
  text: string;
  completed: boolean;
}

const ShoppingList: React.FC = () => {
  const [items, setItems] = useState<ShoppingItem[]>([]);
  const [newItemText, setNewItemText] = useState('');

  const addItem = () => {
    if (newItemText.trim()) {
      setItems([...items, { id: Date.now().toString(), text: newItemText.trim(), completed: false }]);
      setNewItemText('');
    }
  };

  const toggleItem = (id: string) => {
    setItems(items.map(item => 
      item.id === id ? { ...item, completed: !item.completed } : item
    ));
  };

  return (
    <div className="shopping-list">
      <Typography variant="h4" className="shopping-list-title">WG</Typography>
      <div className="shopping-list-input">
        <TextField
          value={newItemText}
          onChange={(e) => setNewItemText(e.target.value)}
          placeholder="Add new item"
          variant="outlined"
          size="small"
          fullWidth
        />
        <IconButton onClick={addItem} color="primary">
          <AddIcon />
        </IconButton>
      </div>
      <List className="shopping-list-items">
        {items.map((item) => (
          <ListItem
            key={item.id}
            dense
            disablePadding
            component="div"
            onClick={() => toggleItem(item.id)}
          >
            <ListItemButton>
              <ListItemIcon>
                <Checkbox
                  edge="start"
                  checked={item.completed}
                  tabIndex={-1}
                  disableRipple
                />
              </ListItemIcon>
              <ListItemText primary={item.text} />
              <ShoppingCartIcon />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
    </div>
  );
};

export default ShoppingList;
