package vn.edu.ute.RecycleView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    private val _students = MutableLiveData<List<Student>>()
    val students: LiveData<List<Student>> get() = _students

    init {
        _students.value = listOf(
            Student("1", "20110001", "Nguyễn Văn An", 3.65, "Công Nghệ Thông Tin", 0xFF059669.toInt()),
            Student("2", "20110002", "Trần Thị Mai", 3.82, "Kỹ Thuật Phần Mềm", 0xFF0284C7.toInt()),
            Student("3", "20110003", "Lê Hoàng Phúc", 3.20, "An Toàn Thông Tin", 0xFFD97706.toInt())
        )
    }

    fun addStudent(student: Student) {
        val currentList = _students.value?.toMutableList() ?: mutableListOf()
        currentList.add(0, student)
        _students.value = currentList
    }

    fun deleteStudent(studentId: String) {
        val currentList = _students.value?.toMutableList() ?: return
        val filtered = currentList.filterNot { it.id == studentId }
        _students.value = filtered
    }
}