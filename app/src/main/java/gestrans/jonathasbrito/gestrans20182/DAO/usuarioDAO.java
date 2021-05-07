package gestrans.jonathasbrito.gestrans20182.DAO;

import java.util.List;

import gestrans.jonathasbrito.gestrans20182.interfaces.IUsuarioDAO;
import gestrans.jonathasbrito.gestrans20182.models.Usuario;

/**
 * Created by Brito on 19/11/2018.
 */

public class usuarioDAO implements IUsuarioDAO{

    @Override
    public boolean salvar(Usuario usuario) {
        return false;
    }

    @Override
    public boolean atualizar(Usuario usuario) {
        return false;
    }

    @Override
    public boolean deletar(Usuario usuario) {
        return false;
    }

    @Override
    public List<Usuario> listar() {
        return null;
    }
}
