package catserver.server.utils;

import catserver.server.CatServer;
import net.minecraftforge.fml.common.FMLLog;
import org.bukkit.Bukkit;
import org.spigotmc.AsyncCatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.function.Predicate;

public class ThreadSafeList<E> extends Vector<E> {
    private static final String message = LanguageUtils.I18nToString("async.caught_async");
    private final boolean warn;

    public ThreadSafeList(boolean warn) {
        this.warn = warn;
    }

    @Override
    public boolean add(E e) {
        if (checkThread()) {
            CatServer.postPrimaryThread(() -> super.add(e));
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return true;
        }
        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        if (checkThread()) {
            CatServer.postPrimaryThread(() -> super.add(index, element));
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return;
        }
        super.add(index, element);
    }

    @Override
    public boolean remove(Object o) {
        if (checkThread()) {
            CatServer.postPrimaryThread(() -> super.remove(o));
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return super.contains(o);
        }
        return super.remove(o);
    }

    @Override
    public synchronized E remove(int index) {
        if (checkThread()) {
            E removeE = super.get(index);
            CatServer.postPrimaryThread(() -> super.remove(removeE));
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return removeE;
        }
        return super.remove(index);
    }

    @Override
    public void clear() {
        if (checkThread()) {
            CatServer.postPrimaryThread(super::clear);
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return;
        }
        super.clear();
    }

    @Override
    public synchronized boolean addAll(Collection<? extends E> c) {
        if (checkThread()) {
            CatServer.postPrimaryThread(() -> super.addAll(c));
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return true;
        }
        return super.addAll(c);
    }

    @Override
    public synchronized boolean addAll(int index, Collection<? extends E> c) {
        if (checkThread()) {
            CatServer.postPrimaryThread(() -> super.addAll(index, c));
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return true;
        }
        return super.addAll(index, c);
    }

    @Override
    public synchronized void addElement(E obj) {
        if (checkThread()) {
            CatServer.postPrimaryThread(() -> super.addElement(obj));
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return;
        }
        super.addElement(obj);
    }

    @Override
    public synchronized void removeElementAt(int index) {
        if (checkThread()) {
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return;
        }
        super.removeElementAt(index);
    }

    @Override
    public synchronized boolean removeAll(Collection<?> c) {
        if (checkThread()) {
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return false;
        }
        return super.removeAll(c);
    }

    @Override
    public synchronized void removeAllElements() {
        if (checkThread()) {
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return;
        }
        super.removeAllElements();
    }

    @Override
    public synchronized boolean removeElement(Object obj) {
        if (checkThread()) {
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return false;
        }
        return super.removeElement(obj);
    }

    @Override
    public synchronized boolean removeIf(Predicate<? super E> filter) {
        if (checkThread()) {
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return false;
        }
        return super.removeIf(filter);
    }

    @Override
    public synchronized Iterator<E> iterator() {
        if (checkThread()) {
            if (warn) {
                FMLLog.log.debug(new UnsupportedOperationException(message));
            }
            return new ArrayList<E>(this).iterator();
        }
        return super.iterator();
    }

    private boolean checkThread() {
        return AsyncCatcher.enabled && !Bukkit.isPrimaryThread();
    }
}
