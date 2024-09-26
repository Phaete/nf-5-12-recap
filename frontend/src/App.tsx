import {useEffect, useState} from 'react'
import './App.css'
import {Route, Routes} from "react-router-dom";
import Board from "./pages/board/Board.tsx";
import {ToDo} from "./types/ToDo.ts";
import axios from "axios";
import Header from "./components/Header/Header.tsx";
import InProgress from "./pages/in_progress/InProgress.tsx";
import Done from "./pages/done/Done.tsx";
import Open from "./pages/open/Open.tsx";

function App() {
	const [todos, setTodos] = useState<ToDo[]>([])

	const fetchAllTodos = () => {
		axios.get("/api/todo")
			.then(response => {
				setTodos(response.data)
			})
			.catch(error => {
				console.error("Error fetching data: " + error)
			})
	}

	useEffect(() => {
		fetchAllTodos()
	}, [])

	return (
		<>
			<Header />
			<Routes>
				<Route path={"/"} element={<Board todos={todos} fetchAll={fetchAllTodos}/>}></Route>
				<Route path={"/open"} element={<Open todos={todos.filter((todo) => todo.status === "OPEN")} fetchAll={fetchAllTodos}/>}></Route>
				<Route path={"/in_progress"} element={<InProgress todos={todos.filter((todo) => todo.status === "IN_PROGRESS")} fetchAll={fetchAllTodos}/>}></Route>
				<Route path={"/done"} element={<Done todos={todos.filter((todo) => todo.status === "DONE")} fetchAll={fetchAllTodos}/>}></Route>
			</Routes>
		</>
	)
}

export default App
