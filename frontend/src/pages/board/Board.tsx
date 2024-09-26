import "./Board.css"
import {BoardProps} from "./BoardProps.ts";
import Done from "../done/Done.tsx";
import InProgress from "../in_progress/InProgress.tsx";
import Open from "../open/Open.tsx";
import axios from "axios";
import {useState} from "react";

export default function Board(props : BoardProps) {

    const [newTodoDescription, setNewTodoDescription] = useState("")

    function handleInputChange(event : React.FormEvent<HTMLInputElement>) {
        setNewTodoDescription(event.currentTarget.value)
    }

    function onSubmitNewTodo(event : React.FormEvent<HTMLFormElement>) {
        event.preventDefault()
        axios.post("/api/todo", {
            description: newTodoDescription,
            status: "OPEN"
        }).then(() => {
            props.fetchAll()
            setNewTodoDescription("")
        })

    }

    return (
        <>
            <div className={"boxed"}>
                <table>
                    <thead>
                    <tr>
                        <th className={"tableHeaderItem"}>Open</th>
                        <th className={"tableHeaderItem"}>In Progress</th>
                        <th className={"tableHeaderItem"}>Done</th>
                    </tr>
                    <tr>
                        <td className={"w-33"}>{props.todos.filter((todo) => todo.status === "OPEN").length}</td>
                        <td className={"w-33"}>{props.todos.filter((todo) => todo.status === "IN_PROGRESS").length}</td>
                        <td className={"w-33"}>{props.todos.filter((todo) => todo.status === "DONE").length}</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td className={"w-33"}><Open todos={props.todos.filter((todo) => todo.status === "OPEN")} fetchAll={props.fetchAll}/></td>
                        <td className={"w-33"}><InProgress todos={props.todos.filter((todo) => todo.status === "IN_PROGRESS")} fetchAll={props.fetchAll}/></td>
                        <td className={"w-33"}><Done todos={props.todos.filter((todo) => todo.status === "DONE")} fetchAll={props.fetchAll}/></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <form className={"align-right mt-5"} onSubmit={onSubmitNewTodo}>
                <input id={"todoInput"} name={"todo"} type={"text"} placeholder={"Todo Description"} value={newTodoDescription} onChange={handleInputChange}></input>
                <button>Add Item</button>
            </form>
        </>
    )
}