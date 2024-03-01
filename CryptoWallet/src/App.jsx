
import './App.css'
import Navbar from './components/Navbar'
import { Outlet } from 'react-router-dom'

function App() {
  

  return (
    <div className="App">
    <header>
      <Navbar />
    </header>
    <main className="container">
      <Outlet />
    </main>
    <footer>
      <Footer></Footer>
    </footer>
  </div>
  )
}

export default App
