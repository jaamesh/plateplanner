import Header from './components/Header.jsx';
import RecipeList from './components/RecipeList.jsx';
import SearchForm from './components/RecipeTextSearch.jsx';
import './App.css'

function App() {
  return (
    <div className='App'>
      <Header />
      <RecipeList />
      <SearchForm />
    </div>
  )
}

export default App
