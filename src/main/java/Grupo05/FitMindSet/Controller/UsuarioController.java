package Grupo05.FitMindSet.Controller;

@RequestMapping("/user")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EmailService emailService;
    private final TokenProvider tokenProvider;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            String userEmail = usuarioService.findEmailById(id);
            usuarioService.delete(id);
            emailService.sendAccountDeletionEmail(userEmail);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{correo}")
    public ResponseEntity<String> eliminarUsuarioPorCorreo(@PathVariable String correo) {
        try {
            usuarioService.eliminarUsuarioPorCorreo(correo);
            emailService.sendAccountDeletionEmail(correo);
            return ResponseEntity.ok("El usuario con el correo " + correo + " ha sido eliminado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

