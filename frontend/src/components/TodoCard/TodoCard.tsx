import './TodoCard.css';
import {useState} from "react";
import axios from "axios";
import {TodoCardProps} from "./TodoCardProps.ts";

export default function TodoCard(props : TodoCardProps) {

	const [canBeEdited, setCanBeEdited] = useState<boolean>(false)
	const [description, setDescription] = useState<string>(props.todo.description)
	const [status, setStatus] = useState<string>(props.todo.status)

	function handleEdit() : void {
		setCanBeEdited(!canBeEdited)
	}

	function handleDescriptionChange(event : React.FormEvent<HTMLInputElement>) {
		setDescription("" + event.currentTarget.value)
	}

	function handleStatusChange(event: React.FormEvent<HTMLInputElement>) {
		console.log(event.currentTarget.value)
		setStatus(event.currentTarget.value.toUpperCase())
	}

	function checkValidStatus() {
		return (status === "OPEN" || status === "IN_PROGRESS" || status === "DONE")
	}

	function handleSave(event : React.FormEvent<HTMLButtonElement>) : void {
		event.preventDefault()
		if (checkValidStatus()) {
			axios.put(`/api/todo/${props.todo.id}`, {description, status})
				.then(() => {
					setCanBeEdited(false)
					props.fetchAll()
				})
		} else {
			alert("Invalid status")
		}
	}

	function handleDelete(event : React.FormEvent<HTMLButtonElement>) : void {
		event.preventDefault()
		axios.delete(`/api/todo/${props.todo.id}`).then(() => props.fetchAll())
	}

	return (
		<div className={"card"}>
			<form>
				<p className={"stacked"}>
					{props.todo.description}
					{canBeEdited && <input className={"ml-10 mr-10"} type={"text"} id={props.todo.id} name={"description"}
                                           placeholder={props.todo.description} onChange={handleDescriptionChange}/>}
				</p>
				<p className={"stacked"}>
					{props.todo.status}
					{canBeEdited && <input className={"ml-10 mr-10"} type={"text"} id={props.todo.id} name={"status"}
                                           placeholder={props.todo.status} onChange={handleStatusChange}/>}
				</p>
				<span>
					{canBeEdited && <button onClick={handleSave}>Save</button>}
				</span>
			</form>
				<span>
					<button type={"button"} onClick={handleEdit}>Edit</button>
				</span>
				<span>
					<button type={"button"} onClick={handleDelete}>Delete</button>
				</span>
		</div>
	)
}