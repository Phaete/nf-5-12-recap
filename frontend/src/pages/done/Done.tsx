import {DoneProps} from "./DoneProps.ts";

export default function Done(props: DoneProps) {
	return (
		<>
			{props.todos.map((todo) => (
				<div key={todo.id}>
					{todo.id}
					{todo.description}
					{todo.status}
				</div>
			))}
			{props.todos.length === 0 && <div>No Done Todos</div>}
		</>
	)
}