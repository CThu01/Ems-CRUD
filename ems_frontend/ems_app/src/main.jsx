import ReactDom from "react-dom/client"
import "./index.css"
import App from "./App"
import { Provider } from "react-redux"
import { store } from "./store/store"
import NavComponent from "./components/nav/Nav.component"

ReactDom.createRoot(document.querySelector("#root")).render(
    <Provider store={store}>
        <App />
    </Provider>

)