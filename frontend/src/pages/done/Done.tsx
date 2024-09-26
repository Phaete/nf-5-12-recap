import {DoneProps} from "./DoneProps.ts";
import TodoCard from "../../components/TodoCard/TodoCard.tsx";

export default function Done(props: DoneProps) {
	return (
		<div className={"align-top"}>
			{props.todos.map((todo) => (
				<TodoCard key={todo.id} todo={todo} fetchAll={props.fetchAll}/>
			))}
			{props.todos.length === 0 && <div>No Done Todos</div>}
		</div>
	)
}