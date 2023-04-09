import styles from '@/styles/algorithm.module.css'
export default function Launch({file}) {
    return (
        <a href={file} download={file} download="jar">
            Download
        </a>
    )
}